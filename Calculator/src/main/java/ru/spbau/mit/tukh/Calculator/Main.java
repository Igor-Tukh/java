package ru.spbau.mit.tukh.Calculator;

import ru.spbau.mit.tukh.Stack.Stack;
import ru.spbau.mit.tukh.Stack.StackImpl;

/**
 * Transforms expression, given as an argument to backward Polish notation and calculate its value.
 * Expression must consist only of operators +, -, *, /, integer numbers and brackets, such as (, ).
 */
public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Incorrect input data.\n");
        } else {
            try {
                Parser p = new Parser(args[0]);
                Stack<Integer> stack = new StackImpl<>();
                Calculator calc = new Calculator(stack);
                System.out.println(calc.getVal(p.toPolish()));
            } catch (IncorrectCharacterException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
