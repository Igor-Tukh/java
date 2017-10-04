package ru.spbau.mit.tukh.hw03.Trie;

import java.io.*;

/**
 * Class Trie. Specialized for storage strings consist of english lowercase literals.
 */
public class Trie implements Serializable {
    private TrieNode root;

    /**
     * Trie constructor.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * add method. Inserts element into the Trie.
     *
     * @param element is string adding to the Trie. Must consist of english lowercase literals.
     * @return true if there wasn't string equal to element yet and false otherwise.
     */
    public boolean add(String element) {
        if (contains(element))
            return false;

        TrieNode cur = root;
        for (int i = 0; i < element.length(); cur = cur.getNext(element.charAt(i)), i++) {
            cur.updateFrequence(1);
            if (cur.getNext(element.charAt(i)) == null) {
                cur.addNext(element.charAt(i));
            }
        }
        cur.updateFrequence(1);
        cur.isTerminal = true;
        return true;
    }

    /**
     * contains method. Checks if there is element in the Trie.
     *
     * @param element is value of string.
     * @return true if string contains in Trie and false otherwise.
     */
    public boolean contains(String element) {
        TrieNode cur = root;

        for (int i = 0; i < element.length(); cur = cur.getNext(element.charAt(i)), i++) {
            if (cur == null) {
                return false;
            }
        }

        return cur != null && cur.isNotEmpty();
    }

    /**
     * remove method. Removes element from Trie.
     *
     * @param element is value of string.
     * @return true if there is a string equal to element in the trie and false otherwise.
     */
    public boolean remove(String element) {
        if (!contains(element)) {
            return false;
        }

        TrieNode cur = root;

        for (int i = 0; i < element.length(); cur = cur.getNext(element.charAt(i)), i++) {
            cur.updateFrequence(-1);
        }
        cur.updateFrequence(-1);
        cur.isTerminal = false;
        return true;
    }

    /**
     * size method. Returns size of Trie.
     *
     * @return size of Trie.
     */
    public int size() {
        return root == null ? 0 : root.getFrequency();
    }

    /**
     * howManyStartsWithPrefix. Counts how many strings in Trie have prefix param prefix.
     *
     * @param prefix is a value of query.
     * @return number of strings in Trie starts with a given prefix.
     */
    public int howManyStartsWithPrefix(String prefix) {
        TrieNode cur = root;

        for (int i = 0; i < prefix.length(); i++) {
            if (cur == null) {
                return 0;
            }
            cur = cur.getNext(prefix.charAt(i));
        }

        return cur == null ? 0 : cur.getFrequency();
    }

    /**
     * serialize method. Writes object to given output stream.
     *
     * @param out is stream to write object in.
     * @throws IOException according to a given stream
     */
    public void serialize(OutputStream out) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(this.root);
        oos.flush();
        oos.close();
    }

    /**
     * deserialize method. Reads object from given output stream.
     *
     * @param in is stream to read object from.
     * @throws IOException according to a given stream
     */
    public void deserialize(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(in);
        this.root = (TrieNode) ois.readObject();
    }

    /**
     * Private class TrieNode (vertexes of Trie).
     */
    private static class TrieNode implements Serializable {
        int frequency;
        private TrieNode[] next;
        boolean isTerminal;

        private TrieNode() {
            next = new TrieNode[26]; // HashMap?
            frequency = 0;
            isTerminal = false;
        }

        private int getFrequency() {
            return frequency;
        }

        private boolean isNotEmpty() {
            return isTerminal;
        }

        private void updateFrequence(int updateValue) {
            frequency += updateValue;
        }

        private TrieNode getNext(char character) {
            return next[character - 'a']; // only lower register literals
        }

        private void addNext(char character) {
            next[character - 'a'] = new TrieNode();
        }
    }
}