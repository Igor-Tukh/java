package ru.spbau.mit.tukh.Calculator;

import java.util.function.BiFunction;

/**
 * BiOperator is class for binary operators, which designation must consist of one character and arguments must be int.
 */
public class BiOperator implements Comparable<BiOperator> {
    private int prior;
    private BiFunction<Integer, Integer, Integer> function;
    private Character symbol;

    BiOperator(BiFunction<Integer, Integer, Integer> function, int priority, Character symbol) {
        this.function = function;
        this.prior = priority;
        this.symbol = symbol;
    }

    /**
     * applies itself to given args.
     *
     * @param arg1 is first argument.
     * @param arg2 is second argument.
     * @return result of application.
     */
    public int apply(int arg1, int arg2) {
        return function.apply(arg1, arg2);

    }

    /**
     * comparator for comparing by operator priority.
     */
    @Override
    public int compareTo(BiOperator other) {
        return this.prior - other.prior;
    }

    /**
     * returns designation of operator.
     *
     * @return operators designation.
     */
    public Character getSymbol() {
        return symbol;
    }
}