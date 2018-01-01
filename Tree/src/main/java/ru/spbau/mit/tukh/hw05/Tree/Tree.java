package ru.spbau.mit.tukh.hw05.Tree;

/**
 * class Tree is realization of binary search tree without remove method.
 * Content class must implement Comparable interface.
 * Note: it isn't balanced.
 *
 * @param <T> is type of value which tree storage.
 */
public class Tree<T extends Comparable<T>> {
    private static class Node<T> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root;
    private int size;

    private Node<T> addNode(Node<T> cur, T value) {
        if (cur == null) {
            return new Node<>(value);
        }

        if (value.compareTo(cur.value) < 0) {
            cur.left = addNode(cur.left, value);
            return cur;
        }

        cur.right = addNode(cur.right, value);
        return cur;
    }

    /**
     * add method. Adds value to the tree if it hasn't added yet.
     *
     * @param value is adding value.
     * @return true if there wasn't givven value in the tree and false otherwise.
     */
    public boolean add(T value) {
        if (contains(value)) {
            return false;
        }

        if (root == null) {
            root = new Node<>(value);
        } else {
            root = addNode(root, value);
        }
        size++;

        return true;
    }

    /**
     * size method returns size of the tree.
     *
     * @return number of elements in the tree.
     */
    public int size() {
        return size;
    }

    /**
     * Contains method checks if there is given value in the tree.
     *
     * @param value is checking value.
     * @return true if there is given value in the tree and false otherwise.
     */
    public boolean contains(T value) {
        Node<T> cur = root;
        while (cur != null) {
            int cmp = cur.value.compareTo(value);
            if (cmp < 0) {
                cur = cur.right;
            } else if (cmp == 0) {
                return true;
            } else {
                cur = cur.left;
            }
        }

        return false;
    }
}