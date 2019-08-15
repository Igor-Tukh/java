package ru.spbau.mit.tukh.Stack;

public interface Stack<T> {
    /**
     * pushes element t to the head of stack.
     *
     * @param t is element to add.
     */
    void push(T t);

    /**
     * removes head element from stack and returns it value.
     *
     * @return value of head element.
     */
    T pop();

    /**
     * gets value of head element.
     *
     * @return first stack element, but doesn't remove it.
     */
    T front();

    /**
     * checks if stack is empty.
     *
     * @return true if stack is empty and false otherwise.
     */
    boolean isEmpty();
}
