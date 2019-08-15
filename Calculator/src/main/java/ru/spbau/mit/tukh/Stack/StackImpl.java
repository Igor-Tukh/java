package ru.spbau.mit.tukh.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Implementation of interface Stack based on ArrayDeque.
 *
 * @param <T> is type of storing value.
 */
public class StackImpl<T> implements Stack<T> {
    private Deque<T> d;

    public StackImpl() {
        d = new ArrayDeque<>();
    }

    @Override
    public void push(T t) {
        d.addFirst(t);
    }

    @Override
    public T pop() {
        return d.removeFirst();
    }

    @Override
    public T front() {
        return d.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return d.isEmpty();
    }
}
