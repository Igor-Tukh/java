package ru.spbau.mit.tukh.cw1.test;

import ru.spbau.mit.tukh.cw1.HashMap.*;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class HashTableTest {
    @Test
    void testSize() {
        HashTable<String, String> ht = new HashTable<String, String>();
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
        HashTable<String, String> ht = new HashTable<String, String>();
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
        HashTable<String, String> ht = new HashTable<String, String>();
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
        HashTable<String, String> ht = new HashTable<String, String>();
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
        HashTable<String, String> ht = new HashTable<String, String>();
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
        HashTable<String, String> ht = new HashTable<String, String>();
        for (int i = 0; i < 20; i++) {
            ht.put("#" + i, ":" + i);
        }
        ht.clear();
        assertEquals(0, ht.size());
        assertEquals(null, ht.get("1"));
    }

    @Test
    void testIter() {
        HashTable<String, String> ht = new HashTable<String, String>();
        ht.put("1", "1");
        ht.put("2", "2");
        ht.put("3", "3");
        ht.put("4", "4");
        Integer cnt = 1;

        for(Map.Entry<String, String> e: ht){
            assertEquals(e.getKey(), cnt.toString());
            assertEquals(e.getValue(), cnt.toString());
            cnt++;
        }
    }


    @Test
    void testIterSet() {
        HashTable<String, String> ht = new HashTable<String, String>();
        ht.put("1", "1");
        ht.put("2", "2");
        ht.put("3", "3");
        ht.put("4", "4");
        Integer cnt = 1;

        for(Map.Entry<String, String> e: ht.entrySet()){
            assertEquals(e.getKey(), cnt.toString());
            assertEquals(e.getValue(), cnt.toString());
            cnt++;
        }
    }
}