package ru.spbau.mit.tukh.Reflector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public class Reflector {
    private static StringBuilder builder = new StringBuilder();
    private static Set<String> imports = new HashSet<>();

    private static int tabsNumber;

    public static void printStructure(Class<?> someClass) throws FileNotFoundException {
        builder.setLength(0);
        imports.clear();
        addImports(someClass);
        imports.forEach(s -> builder.append("import ").append(s).append(";\n"));
        builder.append("\n");
        printBody(someClass);

        try (PrintWriter out = new PrintWriter(new File(someClass.getSimpleName() + ".java"))) {
            out.print(builder.toString());
        }
    }

    private static void addImports(Class<?> someClass) {
        for (Class<?> importInterface : someClass.getInterfaces()) {
            addImport(importInterface);
        }
        for (Field importField : someClass.getDeclaredFields()) {
            addImport(importField.getDeclaringClass());
        }
        for (Class<?> importClass : someClass.getDeclaredClasses()) {
            addImports(importClass);
        }
        for (Method method : someClass.getDeclaredMethods()) {
            for (Class<?> argType : method.getParameterTypes()) {
                addImport(argType);
            }
        }
        for (Constructor constructor : someClass.getDeclaredConstructors()) {
            for (Class<?> argType : constructor.getParameterTypes()) {
                addImport(argType);
            }
        }
    }

    private static void addImport(Class<?> importClass) {
        if (!importClass.isPrimitive() && !importClass.getCanonicalName().contains("[]")) {
            imports.add(importClass.getPackage().getName() + ".*");
        }
    }

    private static void printBody(Class<?> someClass) {
        addSeveralTabs();
        builder.append(Modifier.toString(someClass.getModifiers()));
        builder.append(someClass.isInterface() ? " " : " class ").append(someClass.getSimpleName());
        addClassGenericParameters(someClass);
        addParentClass(someClass);
        addInterfaces(someClass);
        builder.append(" {\n");

        tabsNumber++;
        for (Class<?> childClass : someClass.getDeclaredClasses()) {
            printBody(childClass);
        }

        for (Field field : someClass.getDeclaredFields()) {
            addField(field);
        }

        for (Constructor constructor : someClass.getConstructors()) {
            addConstructor(constructor);
        }

        for (Method method : someClass.getMethods()) {
            addMethod(method);
        }

        tabsNumber--;
        addSeveralTabs();
        builder.append("}\n\n");
    }

    private static void addSeveralTabs() {
        for (int i = 0; i < tabsNumber; i++) {
            builder.append('\t');
        }
    }

    private static String getClassGenericParameters(Class<?> someClass) {
        TypeVariable<? extends Class<?>>[] params = someClass.getTypeParameters();
        if (params.length == 0) {
            return "";
        }
        return Arrays.stream(params)
                .map(TypeVariable::getName)
                .collect(Collectors.joining(", ", "<", ">"));
    }

    private static void addClassGenericParameters(Class<?> someClass) {
        builder.append(getClassGenericParameters(someClass));
    }

    private static void addMethodGenericParameters(Method method) {
        Type[] params = method.getTypeParameters();
        if (params.length == 0) {
            return;
        }
        builder.append(Arrays.stream(params)
                .map(Type::getTypeName)
                .collect(Collectors.joining(", ", " <", "> ")));

    }

    private static void addParentClass(Class<?> someClass) {
        Class<?> parent = someClass.getSuperclass();
        if (parent == null) {
            return;
        }
        builder.append(" extends ").append(parent.getName());
        addClassGenericParameters(parent);
    }

    private static void addInterfaces(Class<?> someClass) {
        Class<?>[] interfaces = someClass.getInterfaces();
        if (interfaces.length == 0) {
            return;
        }
        builder.append(" implements ");
        builder.append(Arrays.stream(interfaces)
                .map(aClass -> aClass.getName() + getClassGenericParameters(aClass))
                .collect(Collectors.joining(", ")));

    }

    private static void addField(Field field) {
        addSeveralTabs();
        builder.append(Modifier.toString(field.getModifiers()));
        builder.append(" ").append(field.getGenericType().getTypeName());
        builder.append(" ").append(field.getName());
        builder.append(" = ");
        addDefaultValue(field.getType());
        builder.append(";\n");
    }

    private static void addConstructor(Constructor constructor) {
        addSeveralTabs();
        builder.append(Modifier.toString(constructor.getModifiers()))
                .append(" ").append(constructor.getDeclaringClass().getSimpleName())
                .append(" ");
        addArguments(constructor);
        addExceptions(constructor);
        builder.append("{ }\n");
    }

    private static void addArguments(Executable executable) {
        int i = 0;
        StringJoiner stringJoiner = new StringJoiner(", ", "(", ") ");
        for (Class<?> argumentType : executable.getParameterTypes()) {
            stringJoiner.add(argumentType.getName() + " arg" + (++i));
        }
        builder.append(stringJoiner.toString());
    }

    private static void addExceptions(Executable executable) {
        Class<?>[] exceptions = executable.getExceptionTypes();
        if (exceptions.length == 0) {
            return;
        }
        builder.append(Arrays.stream(exceptions).map(Class::getName)
                .collect(Collectors.joining(", ", "throws ", " ")));
    }

    private static void addMethod(Method method) {
        addSeveralTabs();
        builder.append(Modifier.toString(method.getModifiers()));
        builder.append(" ");
        addMethodGenericParameters(method);
        builder.append(" ");
        builder.append(method.getReturnType().getName()).append(" ").append(method.getName());
        addArguments(method);
        addExceptions(method);

        if (Modifier.isAbstract(method.getModifiers()) || Modifier.isNative(method.getModifiers())) {
            builder.append(";");
        } else {
            builder.append("{\n");
            addSeveralTabs();
            builder.append("\t");
            builder.append("return ");
            addDefaultValue(method.getReturnType());
            builder.append(";\n");
            addSeveralTabs();
            builder.append("}");
        }
        builder.append("\n\n");
    }

    private static void addDefaultValue(Class<?> someClass) {
        if (someClass.equals(Character.TYPE) ||
                someClass.equals(Double.TYPE) ||
                someClass.equals(Float.TYPE) ||
                someClass.equals(Integer.TYPE) ||
                someClass.equals(Long.TYPE) ||
                someClass.equals(Byte.TYPE) ||
                someClass.equals(Short.TYPE)) {
            builder.append("0");
        } else if (someClass.equals(String.class)) {
            builder.append("\"\"");
        } else if (someClass.equals(Boolean.TYPE)) {
            builder.append("false");
        } else {
            builder.append("null");
        }
    }

    public static void diffClasses(Class<?> firstClass, Class<?> secondClass) {
        builder.setLength(0);

        Set<Method> secondClassMethods = new HashSet<>();
        secondClassMethods.addAll(Arrays.asList(secondClass.getDeclaredMethods()));

        for (Method firstClassMethod : firstClass.getDeclaredMethods()) {
            boolean methodWasFound = true;
            try {
                Method secondClassMethod = secondClass.getDeclaredMethod(firstClassMethod.getName(), firstClassMethod.getParameterTypes());
                if (!methodsAreEqual(firstClassMethod, secondClassMethod)) {
                    methodWasFound = false;
                } else {
                    secondClassMethods.remove(secondClassMethod);
                }
            } catch (NoSuchMethodException e) {
                methodWasFound = false;
            }
            if (!methodWasFound) {
                builder.append("First class method ").append(firstClassMethod.getName()).append(" doesn't exist in the second class:\n");
                addMethod(firstClassMethod);
                builder.append("\n");
            }
        }

        for (Method secondClassMethod : secondClassMethods) {
            builder.append("Second class method ").append(secondClassMethod.getName()).append(" doesn't exist in the first class:\n");
            addMethod(secondClassMethod);
            builder.append("\n");
        }

        System.out.println(builder.toString());

        Set<Field> secondClassFields = new HashSet<>();
        secondClassFields.addAll(Arrays.asList(secondClass.getDeclaredFields()));

        for (Field firstClassField : firstClass.getDeclaredFields()) {
            boolean fieldWasFound = true;
            try {
                Field secondClassField = secondClass.getField(firstClassField.getName());
                if (!fieldsAreEqual(firstClassField, secondClassField)) {
                    fieldWasFound = false;
                } else {
                    secondClassFields.remove(secondClassField);
                }
            } catch (NoSuchFieldException e) {
                fieldWasFound = false;
            }
            if (!fieldWasFound) {
                builder.append("First class method ").append(firstClassField.getName()).append(" doesn't exist in the second class:\n");
                addField(firstClassField);
                builder.append("\n");
            }
        }

        for (Field secondClassField : secondClassFields) {
            builder.append("Second class method ").append(secondClassField.getName()).append(" doesn't exist in the first class:\n");
            addField(secondClassField);
            builder.append("\n");
        }
    }

    private static boolean methodsAreEqual(Method firstMethod, Method secondMethod) {
        return firstMethod.getName().equals(secondMethod.getName()) &&
                firstMethod.getReturnType().getTypeName().equals(secondMethod.getReturnType().getTypeName())
                && Arrays.equals(firstMethod.getGenericParameterTypes(), secondMethod.getGenericParameterTypes());
    }

    private static boolean fieldsAreEqual(Field firstField, Field secondField) {
        return firstField.getName().equals(secondField.getName()) && firstField.getGenericType().equals(secondField.getGenericType());
    }

    public static void main(String[] args) throws FileNotFoundException {
        printStructure(Reflector.class);
        diffClasses(Reflector.class, Reflector.class);
    }
}
