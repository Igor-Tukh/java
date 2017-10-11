package ru.spbau.mit.tukh.cw1.HashMap;

/**
 * List class (Linked list)
 * Specialized for storage (key, value) pair<string, string> type and searching in itself by key.
 */

public class List<K, V> {
    private int size;
    private ListNode<K, V> head;
    private ListNode<K, V> tail;

    /**
     * getSize method. Returns size of List.
     *
     * @return size of List.
     */

    public int getSize() {
        return size;
    }

    /**
     * findNode method. Returns ListNode with a key field equals to param key and null otherwise.
     *
     * @param key search string param;
     * @return Node with a key field equals to param key and null otherwise.
     */

    private ListNode<K, V> findNode(K key) {
        ListNode<K, V> node = head;

        while (node != null) {
            if (node.key.equals(key)) {
                return node;
            }
            node = node.next;
        }

        return null;
    }

    /**
     * get method. Returns value fit key if it contains in List and null otherwise.
     *
     * @param key searching string param;
     * @return value fit key if it contains in List  and null otherwise
     */

    public V get(K key) {
        ListNode<K, V> node = findNode(key);
        if (node != null) {
            return node.value;
        } else {
            return null;
        }
    }

    /**
     * put method. Inserts pair (key, value) and returns null if key doesn't contain in List yet and value
     * otherwise.
     *
     * @param key   string key value;
     * @param value string value value;
     * @return null if key doesn't contain in List yet and value otherwise.
     */

    public V put(K key, V value) {
        ListNode<K, V> previousNode = findNode(key);
        if (previousNode == null) {
            size++;
            ListNode<K, V> node = new ListNode<K, V>(key, value);
            if (tail == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            return null;
        } else {
            V previousVal = previousNode.value;
            previousNode.value = value;
            return previousVal;
        }
    }

    /**
     * remove method. Returns value fit param key and null if there isn't key in list.
     *
     * @param key string key value
     * @return value fit param key and null if there isn't key in list.
     */

    public V remove(K key) {
        ListNode<K, V> node = findNode(key);

        if (node == null) {
            return null;
        } else {
            if (node == head) {
                if (tail == head)
                    tail = null;
                head = node.next;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            size--;
            return node.value;
        }
    }

    /**
     * contains method. Checks if key contains in List;
     *
     * @param key checking key.
     * @return true if key contains, false otherwise.
     */

    public boolean contains(K key) {
        return findNode(key) != null;
    }

    /**
     * clear method. Clears list content.
     */

    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * getKey method. Returns key of node which order number is index (from zero to size minus one) or null string if
     * index is greater than size of List or lower than zero.
     *
     * @param index in List of searching node.
     */

    public K getKey(int index) {
        if (index >= size || index < 0) {
            return null;
        } else {
            ListNode<K, V> cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur.key;
        }
    }

    /**
     * Inner class ListNode for nodes of List. Consist of key and value string fields and ListNode links next(next node
     * in List) and prev(previous node in List)
     */

    private static class ListNode<K, V> {
        private K key;
        private V value;
        private ListNode<K, V> next;
        private ListNode<K, V> prev;

        /**
         * ListNode class constructor.
         *
         * @param key   initilization string value of key field
         * @param value initilization string value of value field
         */

        ListNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}