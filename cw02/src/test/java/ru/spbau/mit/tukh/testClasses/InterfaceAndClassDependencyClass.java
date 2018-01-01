package ru.spbau.mit.tukh.testClasses;

public class InterfaceAndClassDependencyClass {
    private final ClassWithOneInterfaceDependency classDependency;
    private final Interface dependency;

    public InterfaceAndClassDependencyClass(Interface dependency, ClassWithOneInterfaceDependency classDependency) {
        this.dependency = dependency;
        this.classDependency = classDependency;
    }
}
