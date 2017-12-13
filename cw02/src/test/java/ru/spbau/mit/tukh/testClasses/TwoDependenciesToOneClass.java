package ru.spbau.mit.tukh.testClasses;

public class TwoDependenciesToOneClass {
    private final CounterClass firstCounter;
    private final CounterClass secondCounter;

    public TwoDependenciesToOneClass(CounterClass firstCounter, CounterClass secondCounter) {
        this.firstCounter = firstCounter;
        this.secondCounter = secondCounter;
    }
}
