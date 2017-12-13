package ru.spbau.mit.tukh.testClasses;

public class CircleDependencyThirdClass {
    public final CircleDependencyFirstClass dependency;

    public CircleDependencyThirdClass(CircleDependencyFirstClass dependency) {
        this.dependency = dependency;
    }
}
