package ru.spbau.mit.tukh.Calculator;

import ru.spbau.mit.tukh.Stack.Stack;

/**
 * Calculator is class which can calculate the value of expression in backward Polish notation.
 */
public class Calculator {
    private Stack<Character> opertaions;
    private Stack<Integer> values;

    public Calculator(Stack<Integer> value) {
        this.values = value;
    }

    /**
     * calculates result of expression in backward Polish notation.
     *
     * @param expression is expression in Polish notation to calculate.
     * @return result of calculation.
     */
    public Integer getVal(String expression) {
        Parser p = new Parser(expression);

        while (p.hasMoreTokens()) {
            p.getSpaces();
            if (!p.hasMoreTokens()) {
                break;
            }

            Integer val;
            if ((val = p.getInt()) != null) {
                values.push(val);
            } else {
                BiOperator op = p.getOperator();
                int a = values.pop();
                int b = values.pop();

                values.push(op.apply(b, a));
            }
        }

        return values.pop();
    }
}
