package ru.spbau.mit.tukh.Calculator;

import ru.spbau.mit.tukh.Stack.StackImpl;

import java.util.LinkedList;
import java.util.Queue;

import ru.spbau.mit.tukh.Stack.*;

/**
 * Parser can parse arithmetic expression and transform it to backward Polish notation.
 */
public class Parser {
    private Queue<Character> symbols;

    public Parser(String expression) {
        symbols = new LinkedList<>();

        for (char c : expression.toCharArray()) {
            symbols.add(c);
        }
    }

    /**
     * transforms given string expression to to backward Polish notation. The expression can contain operators +, -, *,
     * /, integer numbers and brackets, such as (, ). It is considered that given expression is correct
     * arithmetic expression.
     *
     * @return expression in backward Polish notation -- result of transformation.
     * @throws IncorrectCharacterException if expression contain unknown character.
     */
    public String toPolish() throws IncorrectCharacterException {
        StringBuilder sb = new StringBuilder();

        Stack<Character> tmp = new StackImpl<>();

        while (symbols.size() > 0) {
            getSpaces();
            if (!hasMoreTokens()) {
                break;
            }

            Integer val = getInt();
            BiOperator op;
            Character c;

            if (val != null) {
                sb.append(val.toString());
            } else if ((c = getOpeningBracket()) != null) {
                tmp.push(c);
            } else if ((c = getClosingBracket()) != null) {
                while (tmp.front() != pairBracket(c)) {
                    sb.append(tmp.pop());
                }
                tmp.pop();
            } else if ((op = getOperator()) != null) {
                BiOperator next;
                while (!tmp.isEmpty() && (next = toOperator(tmp.front())) != null && next.compareTo(op) > 0) {
                    sb.append(tmp.pop());
                }
                tmp.push(op.getSymbol());
            } else if (symbols.peek() == ' ') {
                symbols.poll();
            } else {
                throw new IncorrectCharacterException("Unknown symbol");
            }
            sb.append(" ");

            getSpaces();
        }

        while (!tmp.isEmpty()) {
            sb.append(tmp.pop());
        }

        return sb.toString();
    }

    private boolean isNumeral() {
        return (symbols.size() > 0 && symbols.peek() <= '9' && symbols.peek() >= '0');
    }

    /**
     * removes spaces from the beginning og expression.
     */
    public void getSpaces() {
        while (symbols.size() > 0 && symbols.peek() == ' ') {
            symbols.poll();
        }
    }

    /**
     * tries to read integer from the beginning of expression.
     *
     * @return parsed value and null if value can't be parsed.
     */
    public Integer getInt() {
        if (isNumeral()) {
            int ans = 0;
            while (symbols.size() > 0 && isNumeral()) {
                ans = ans * 10 + symbols.poll() - '0';
            }
            return ans;
        }
        return null;
    }

    /**
     * tries to read operator from the beginning of expression.
     *
     * @return parsed operator and null if operator can't be parsed.
     */
    public BiOperator getOperator() {
        if (ExpressionOperators.operators.containsKey(symbols.peek())) {
            return ExpressionOperators.operators.get(symbols.poll());
        }
        return null;
    }

    /**
     * check if there are more tokens to parse in the expression.
     *
     * @return true if there is at least one more token (including space) in the expression and false otherwise.
     */
    public boolean hasMoreTokens() {
        return (symbols.size() > 0);
    }

    private BiOperator toOperator(char c) {
        if (ExpressionOperators.operators.containsKey(c)) {
            return ExpressionOperators.operators.get(c);
        }
        return null;
    }

    private Character getOpeningBracket() {
        if (symbols.peek() == '(')
            return symbols.poll();
        return null;
    }

    private Character getClosingBracket() {
        if (symbols.peek() == ')')
            return symbols.poll();
        return null;
    }

    private Character pairBracket(char c) {
        if (c == ')') {
            return '(';
        }
        return ')';
    }
}
