package ru.spbau.mit.tukh.testClasses;

public class ClassWithOneInterfaceDependency {
    public final Interface dependency;

    public ClassWithOneInterfaceDependency(Interface dependency) {
        this.dependency = dependency;
    }
}