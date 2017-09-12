package ru.spbau.mit.tukh.hw01.HashTable;

import ru.spbau.mit.tukh.hw01.List.*;

/**
 * HashTable class (hash-table). Specialized for storage (key, value) pair<string, string> type and searching in itself
 * by key.
 */

public class HashTable {
    private static int mod = 514229;
    private List[] ht;
    private int size;

    /**
     * HashTable constructor
     */

    public HashTable() {
        ht = new List[mod];
        for (int i = 0; i < mod; i++)
            ht[i] = new List();
        size = 0;
    }

    private int getHash(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (int) ((long) hash * 239 + str.charAt(i)) % mod;
        }
        return hash;
    }

    /**
     * size() method. Returns number of keys in hash-table.
     *
     * @return (int) number of keys in hash-table.
     */

    public int size() {
        return size;
    }

    /**
     * contains method. Returns boolean value of statement hash-table contains key.
     *
     * @param key string key param;
     * @return (boolean) value of statement hash-table contains key.
     */

    public boolean contains(String key) {
        return ht[getHash(key)].contains(key);
    }

    /**
     * get method. Returns value fit key if it contains in hash-table and null otherwise.
     *
     * @param key searching string param;
     * @return string value fit key if it contains in hash-table and null otherwise
     */

    public String get(String key) {
        return ht[getHash(key)].get(key);
    }

    /**
     * put method. Inserts pair (key, value) and returns null if key doesn't contain in hash-table yet and value
     * otherwise.
     *
     * @param key   string key value;
     * @param value string value value;
     * @return null if key doesn't contain in hash-table yet and (string) value otherwise.
     */


    public String put(String key, String value) {
        int ind = getHash(key);
        size -= ht[ind].getSize();
        String ans = ht[ind].put(key, value);
        size += ht[ind].getSize();
        return ans;
    }

    /**
     * remove method. Returns value fit param key and null if there isn't key in hash-table.
     *
     * @param key string key value
     * @return value fit param key and null if there isn't key in hash-table.
     */

    public String remove(String key) {
        int ind = getHash(key);
        size -= ht[ind].getSize();
        String ans = ht[ind].remove(key);
        size += ht[ind].getSize();
        return ans;
    }

    /**
     * clear method. Clears hash-table content.
     */

    public void clear() {
        for (int i = 0; i < mod; i++) {
            ht[i].clear();
        }
        size = 0;
    }
}
