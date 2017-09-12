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
     * List class constructor.
     */

    public List() {
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * getSize method. Returns size of List.
     *
     * @return (int) size of List.
     */

    public int getSize() {
        return size;
    }

    /**
     * findNode method. Returns ListNode with a key field equals to param key and null otherwise.
     *
     * @param key search string param;
     * @return ListNode with a key field equals to param key and null otherwise.
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
     * @return string value fit key if it contains in List  and null otherwise
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
     * @return null if key doesn't contain in List yet and (string) value otherwise.
     */

    public String put(String key, String value) {
        ListNode previousNode = findNode(key);
        if (previousNode == null) {
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
     * @param key string key param
     * @return boolean: true if key contains, false otherwise.
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
     * Inner class ListNode for nodes of List. Consist of key and value string fields and ListNode links next(next node
     * in List) and prev(previous node in List)
     */
    private class ListNode {
        private String key;
        private String value;
        private ListNode next, prev;

        /**
         * ListNode class constructor.
         *
         * @param _key   initilization string value of key field
         * @param _value initilization string value of value field
         */

        ListNode(String _key, String _value) {
            key = _key;
            value = _value;
            next = null;
            prev = null;
            size++;
        }
    }
}