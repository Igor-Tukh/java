package ru.spbau.mit.tukh.testClasses;

public class CircleDependencyFirstClass {
    public final CircleDependencySecondClass dependency;

    public CircleDependencyFirstClass(CircleDependencySecondClass dependency) {
        this.dependency = dependency;
    }
}
