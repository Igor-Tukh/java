package ru.spbau.mit.tukh.hw01.List;

/**
 * List class (Linked list)
 * Specialized for storage (key, value) pair<string, string> type and searching in itself by key.
 */

public class List {
    private int size;
    private ListNode head;
    private ListNode tail;

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

    private ListNode findNode(String key) {
        ListNode node = head;

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

    public String get(String key) {
        ListNode node = findNode(key);
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

    public String put(String key, String value) {
        ListNode previousNode = findNode(key);
        if (previousNode == null) {
            size++;
            ListNode node = new ListNode(key, value);
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
            String previousVal = previousNode.value;
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

    public String remove(String key) {
        ListNode node = findNode(key);

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

    public boolean contains(String key) {
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

    public String getKey(int index) {
        if (index >= size || index < 0) {
            return null;
        } else {
            ListNode cur = head;
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

    private static class ListNode {
        private String key;
        private String value;
        private ListNode next;
        private ListNode prev;

        /**
         * ListNode class constructor.
         *
         * @param key   initilization string value of key field
         * @param value initilization string value of value field
         */

        ListNode(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}