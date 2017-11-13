package ru.spbau.mit.tukh.hw01.test;

import org.junit.jupiter.api.Test;
import ru.spbau.mit.tukh.hw01.List.*;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {
    @Test
    void testGetSize() {
        List l = new List();
        l.put("1", "C++");
        l.put("2", "Java");
        assertEquals(2, l.getSize());
        l.put("2", "C#");
        l.put("3", "Ruby");
        assertEquals(3, l.getSize());
        l.remove("2");
        assertEquals(2, l.getSize());
        l.remove("4");
        assertEquals(2, l.getSize());
    }

    @Test
    void testGet() {
        List l = new List();
        l.put("1", "C++");
        l.put("2", "Java");
        l.put("3", "C#");
        l.put("4", "Ruby");
        assertEquals("C++", l.get("1"));
        assertEquals("Java", l.get("2"));
        assertEquals("C#", l.get("3"));
        assertEquals("Ruby", l.get("4"));
        l.remove("1");
        assertEquals(null, l.get("1"));
        l.put("1", "Pascal");
        assertEquals("Pascal", l.get("1"));
    }

    @Test
    void testPut() {
        List l = new List();
        assertEquals(null, l.put("1", "C++"));
        assertEquals("C++", l.put("1", "Java"));
        assertEquals("Java", l.put("1", "Ruby"));
        l.remove("1");
        assertEquals(null, l.put("1", "Java"));
    }

    @Test
    void testRemove() {
        List l = new List();
        l.put("1", "C++");
        l.put("2", "Java");
        l.put("3", "C#");
        l.put("4", "Ruby");
        assertEquals(null, l.remove("5"));
        assertEquals("C++", l.remove("1"));
        assertEquals("Java", l.remove("2"));
        assertEquals("C#", l.remove("3"));
        assertEquals("Ruby", l.remove("4"));
    }

    @Test
    void testContains() {
        List l = new List();
        assertEquals(false, l.contains("1"));
        l.put("1", "C++");
        assertEquals(true, l.contains("1"));
        l.put("1", "Java");
        assertEquals(true, l.contains("1"));
        l.remove("1");
        assertEquals(false, l.contains("1"));
        l.put("1", "Java");
        assertEquals(true, l.contains("1"));
    }

    @Test
    void testClear() {
        List l = new List();
        l.put("1", "C++");
        l.put("2", "Java");
        l.put("3", "C#");
        l.put("4", "Ruby");
        assertEquals(4, l.getSize());
        l.clear();
        assertEquals(0, l.getSize());
        assertEquals(null, l.get("1"));
    }

    @Test
    void testGetKey() {
        List l = new List();
        l.put("1", "C++");
        l.put("2", "Java");
        l.put("3", "C#");
        l.put("4", "Ruby");
        assertEquals("1", l.getKey(0));
        assertEquals("2", l.getKey(1));
        assertEquals("3", l.getKey(2));
        assertEquals("4", l.getKey(3));
        assertEquals(null, l.getKey(-1));
        assertEquals(null, l.getKey(4));
    }
}