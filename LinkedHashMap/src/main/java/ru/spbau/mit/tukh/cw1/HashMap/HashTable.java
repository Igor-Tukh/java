package ru.spbau.mit.tukh.cw1.HashMap;

import java.util.*;

/**
 * HashTable class (hash-table). Specialized for storage (key, value) pairs and searching in itself
 * by key.
 */

public class HashTable<K, V> extends AbstractMap<K, V> implements Iterable<Map.Entry<K, V>>{
    private List<K, V>[] hashTable;
    private int size;
    private ArrayList<Map.Entry<K, V>> data = new ArrayList<>();

    private static class MyEntry<K, V> implements Entry<K,V>{
        private K key;
        private V value;

        MyEntry<K, V> next;

        MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V v) {
            V oldValue = value;
            value = v;
            return oldValue;
        }
    }

    public class MySet<V> extends AbstractSet<V> {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public Iterator<V> iterator() {
            return iterator();
        }
    }


    /**
     * HashTable constructor
     */

    public HashTable() {
        hashTable = new List[8];
        for (int i = 0; i < 8; i++) {
            hashTable[i] = new List<K, V>();
        }
    }

    private int getHash(K key) {
        return (int)((long)key.hashCode() + Integer.MAX_VALUE) % hashTable.length;
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

    public boolean contains(K key) {
        return hashTable[getIndex(key)].contains(key);
    }

    /**
     * get method. Returns value fit key if it contains in hash-table and null otherwise.
     *
     * @param key searching string param;
     * @return string value fit key if it contains in hash-table and null otherwise
     */

    @Override
    public V get(Object key) {
        return hashTable[getIndex((K)key)].get((K)key);
    }

    /**
     * put method. Inserts pair (key, value) and returns null if key doesn't contain in hash-table yet and value
     * otherwise.
     *
     * @param key   string key value;
     * @param value string value value;
     * @return null if key doesn't contain in hash-table yet and value otherwise.
     */

    @Override
    public V put(K key, V value) {
        data.add(new MyEntry<K, V>(key, value));
        if (size >= hashTable.length) {
            rebuild();
        }
        int ind = getIndex(key);
        size -= hashTable[ind].getSize();
        V ans = hashTable[ind].put(key, value);
        size += hashTable[ind].getSize();
        return ans;
    }

    /**
     * remove method. Returns value fit param key and null if there isn't key in hash-table.
     *
     * @param key string key value
     * @return value fit param key and null if there isn't key in hash-table.
     */

    @Override
    public V remove(Object key) {
        int ind = getIndex((K)key);
        size -= hashTable[ind].getSize();
        V ans = hashTable[ind].remove((K)key);
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

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new MySet<>();
    }

    private int getIndex(K key) {
        return getHash(key) % hashTable.length;
    }

    private void rebuild() {
        int len = hashTable.length;
        List<K, V>[] old = hashTable;
        hashTable = new List[2 * len];

        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = new List<K, V>();
        }

        for (int i = 0; i < len; i++) {
            if (old[i] != null) {
                for (int j = 0; j < old[i].getSize(); j++) {
                    K key = old[i].getKey(j);
                    V val = old[i].get(key);
                    hashTable[getIndex(key)].put(key, val);
                }
            }
        }
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new MyIterator<K, V>();
    }

    private class MyIterator<K, V> implements Iterator<Entry<K, V>> {
        private int pos;

        @Override
        public boolean hasNext() {
            return pos != size();
        }

        @Override
        public Entry<K, V> next() {
            pos++;
            return (Entry<K, V>) data.get(pos - 1);
        }
    }
}