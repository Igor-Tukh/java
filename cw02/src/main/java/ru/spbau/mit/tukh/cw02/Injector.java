package ru.spbau.mit.tukh.cw02;

import java.lang.reflect.Constructor;
import java.util.*;

public class Injector {
    private static Set<Class<?>> markedClasses = new HashSet<>();
    private static Map<Class<?>, Object> builtObjects = new HashMap<>();

    /**
     * Create and initialize object of `rootClassName` class using classes from
     * `implementationClassNames` for concrete dependencies.
     */
    public static Object initialize(String rootClassName, List<String> availableImplementations) throws Exception {
        Class<?> rootClass = null;
        try {
            rootClass = Class.forName(rootClassName);
        } catch (ClassNotFoundException e) {
            throw new ImplementationNotFoundException("No implementation for " + rootClassName);
        }
        markedClasses.clear();
        builtObjects.clear();
        return buildObject(rootClass, availableImplementations);
    }

    /**
     * Builds object: looks for implementations and build dependencies recursively (similar to dfs).
     */
    private static Object buildObject(Class<?> currentClass, List<String> availableImplementations) throws Exception {
        if (builtObjects.containsKey(currentClass)) {
            return builtObjects.get(currentClass);
        }
        markedClasses.add(currentClass);

        Constructor constructor = currentClass.getConstructors()[0]; // According to task, there is only one constructor
        Class[] argumentClasses = constructor.getParameterTypes();
        ArrayList<Object> arguments = new ArrayList<>();

        for (Class argumentClass : argumentClasses) {
            if (markedClasses.contains(argumentClass) && !builtObjects.containsKey(argumentClass)) {
                throw new InjectionCycleException("Circle dependency in " + argumentClass.getName());
            }
        }

        for (Class argumentClass : argumentClasses) {
            Class<?> argument = null;
            int implementationsCount = 0;

            for (String implementationName : availableImplementations) {
                Class implementation = Class.forName(implementationName);
                if (argumentClass.isAssignableFrom(implementation)) {
                    argument = implementation;
                    implementationsCount++;
                }
            }

            if (implementationsCount > 1) {
                throw new AmbiguousImplementationException("Multiple implementations of " + argumentClass.getName());
            } else if (implementationsCount == 0) {
                throw new ImplementationNotFoundException("No implementation for " + argumentClass.getName());
            } else {
                arguments.add(buildObject(argument, availableImplementations));
            }
        }

        Object res = constructor.newInstance(arguments.toArray());

        builtObjects.put(currentClass, res);
        return res;
    }
}