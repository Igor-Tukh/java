package ru.spbau.mit.tukh.Calculator;

import java.util.HashMap;
import java.util.Map;

/**
 * class with four main binary operators.
 */
public class ExpressionOperators {
    static Map<Character, BiOperator> operators;

    static {
        operators = new HashMap<>();
        operators.put('+', new BiOperator((a, b) -> a + b, 3, '+'));
        operators.put('-', new BiOperator((a, b) -> a - b, 3, '-'));
        operators.put('*', new BiOperator((a, b) -> a * b, 5, '*'));
        operators.put('/', new BiOperator((a, b) -> a / b, 5, '/'));
    }
}
