package ru.spbau.mit.tukh.hw01.test;

import org.junit.jupiter.api.Test;
import ru.spbau.mit.tukh.hw01.HashTable.HashTable;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    @Test
    void testSize() {
        HashTable ht = new HashTable();
        for (int i = 0; i < 20; i++) {
            assertEquals(i, ht.size());
            ht.put("#" + i, ":" + i);
        }
        for (int i = 0; i < 20; i++) {
            assertEquals(20 - i, ht.size());
            ht.remove("#" + i);
        }
        for (int i = 0; i < 2; i++) {
            assertEquals(0, ht.size());
            ht.remove("#" + i);
        }
    }

    @Test
    void testContains() {
        HashTable ht = new HashTable();
        for (int i = 0; i < 20; i++) {
            ht.put("#" + i, ":" + i);
            assertEquals(true, ht.contains("#" + i));
            ht.put("#" + i, "::" + i);
            assertEquals(true, ht.contains("#" + i));
        }
        for (int i = 0; i < 20; i++) {
            ht.remove("#" + i);
            assertEquals(false, ht.contains("#" + i));
        }
    }

    @Test
    void testGet() {
        HashTable ht = new HashTable();
        for (int i = 0; i < 20; i++) {
            ht.put("#" + i, ":" + i);
            assertEquals(":" + i, ht.get("#" + i));
            ht.put("#" + i, "::" + i);
            assertEquals("::" + i, ht.get("#" + i));
        }
        for (int i = 0; i < 20; i++) {
            ht.remove("#" + i);
            assertEquals(null, ht.get("#" + i));
        }
    }

    @Test
    void testPut() {
        HashTable ht = new HashTable();
        for (int i = 0; i < 20; i++) {
            assertEquals(null, ht.put("#" + i, ":" + i));
            assertEquals(":" + i, ht.put("#" + i, "::" + i));
        }
        for (int i = 0; i < 20; i++) {
            ht.remove("#" + i);
            assertEquals(null, ht.put("#" + i, ":" + i));
        }
    }

    @Test
    void testRemove() {
        HashTable ht = new HashTable();
        for (int i = 0; i < 20; i++) {
            assertEquals(null, ht.remove("#" + i));
            ht.put("#" + i, ":" + i);
            assertEquals(":" + i, ht.remove("#" + i));
            ht.put("#" + i, "::" + i);
            assertEquals("::" + i, ht.remove("#" + i));
        }
    }

    @Test
    void testClear() {
        HashTable ht = new HashTable();
        for (int i = 0; i < 20; i++) {
            ht.put("#" + i, ":" + i);
        }
        ht.clear();
        assertEquals(0, ht.size());
        assertEquals(null, ht.get("1"));
    }
}