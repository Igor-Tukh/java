package ru.spbau.mit.tukh.hw01.HashTable;

import ru.spbau.mit.tukh.hw01.List.*;

/**
 * HashTable class (hash-table). Specialized for storage (key, value) pair<string, string> type and searching in itself
 * by key.
 */

public class HashTable {
    private List[] hashTable;
    private int size;

    /**
     * HashTable constructor
     */

    public HashTable() {
        hashTable = new List[8];
        for (int i = 0; i < 8; i++) {
            hashTable[i] = new List();
        }
    }

    private int getHash(String str) {
        int hash = 0;
        int mod = 514229;
        for (int i = 0; i < str.length(); i++) {
            hash = (int) ((((long) hash * 239) % mod + mod + str.charAt(i)) % mod);
        }
        return hash;
    }

    /**
     * size method. Returns number of keys in hash-table.
     *
     * @return number of keys in hash-table.
     */

    public int size() {
        return size;
    }

    /**
     * contains method. Returns boolean value of statement hash-table contains key.
     *
     * @param key string key param;
     * @return value of statement hash-table contains key.
     */

    public boolean contains(String key) {
        return hashTable[getIndex(key)].contains(key);
    }

    /**
     * get method. Returns value fit key if it contains in hash-table and null otherwise.
     *
     * @param key searching string param;
     * @return string value fit key if it contains in hash-table and null otherwise
     */

    public String get(String key) {
        return hashTable[getIndex(key)].get(key);
    }

    /**
     * put method. Inserts pair (key, value) and returns null if key doesn't contain in hash-table yet and value
     * otherwise.
     *
     * @param key   string key value;
     * @param value string value value;
     * @return null if key doesn't contain in hash-table yet and value otherwise.
     */

    public String put(String key, String value) {
        if (size >= hashTable.length) {
            rebuild();
        }
        int ind = getIndex(key);
        size -= hashTable[ind].getSize();
        String ans = hashTable[ind].put(key, value);
        size += hashTable[ind].getSize();
        return ans;
    }

    /**
     * remove method. Returns value fit param key and null if there isn't key in hash-table.
     *
     * @param key string key value
     * @return value fit param key and null if there isn't key in hash-table.
     */

    public String remove(String key) {
        int ind = getIndex(key);
        size -= hashTable[ind].getSize();
        String ans = hashTable[ind].remove(key);
        size += hashTable[ind].getSize();
        return ans;
    }

    /**
     * clear method. Clears hash-table content.
     */

    public void clear() {
        for (List l : hashTable) {
            l.clear();
        }
        size = 0;
    }

    private int getIndex(String key) {
        return getHash(key) % hashTable.length;
    }

    private void rebuild() {
        int len = hashTable.length;
        List[] old = hashTable;
        hashTable = new List[2 * len];

        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = new List();
        }

        for (int i = 0; i < len; i++) {
            if (old[i] != null) {
                for (int j = 0; j < old[i].getSize(); j++) {
                    String key = old[i].getKey(j);
                    String val = old[i].get(key);
                    hashTable[getIndex(key)].put(key, val);
                }
            }
        }
    }
}
