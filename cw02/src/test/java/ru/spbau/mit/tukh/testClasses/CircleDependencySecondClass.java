package ru.spbau.mit.tukh.testClasses;

public class CircleDependencySecondClass {
    public final CircleDependencyThirdClass dependency;

    public CircleDependencySecondClass(CircleDependencyThirdClass dependency) {
        this.dependency = dependency;
    }
}
