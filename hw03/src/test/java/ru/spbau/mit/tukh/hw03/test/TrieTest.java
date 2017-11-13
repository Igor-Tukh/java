package ru.spbau.mit.tukh.hw03.test;

import ru.spbau.mit.tukh.hw03.Trie.*;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class TrieTest {
    @Test
    public void testAddSimple() {
        Trie tr = new Trie();
        assertTrue(tr.add("it"));
        assertTrue(tr.add("is"));
    }

    @Test
    public void testAddDuplicate() {
        Trie tr = new Trie();
        assertTrue(tr.add("bor"));
        assertFalse(tr.add("bor"));
    }

    @Test
    public void testAddAfterRemove() {
        Trie tr = new Trie();
        assertTrue(tr.add("is"));
        tr.remove("is");
        assertTrue(tr.add("is"));
    }

    @Test
    public void testContainsSimple() {
        Trie tr = new Trie();
        assertFalse(tr.contains("aba"));
        assertFalse(tr.contains("abacabadabacabaeabacabadabacaba"));
        tr.add("aba");
        tr.add("abacabadabacabaeabacabadabacaba");
        assertTrue(tr.contains("aba"));
        assertTrue(tr.contains("abacabadabacabaeabacabadabacaba"));
        tr.remove("aba");
        assertFalse(tr.contains("aba"));
        tr.add("aba");
        assertTrue(tr.contains("aba"));
    }

    @Test
    public void testContainsAfterRemove() {
        Trie tr = new Trie();
        tr.add("aba");
        tr.remove("aba");
        assertFalse(tr.contains("aba"));
        tr.add("aba");
        assertTrue(tr.contains("aba"));
    }

    @Test
    public void testRemove() {
        Trie tr = new Trie();
        tr.add("expected");
        tr.add("ex");
        assertTrue(tr.remove("ex"));
        assertFalse(tr.remove("ex"));
        assertTrue(tr.remove("expected"));
    }

    @Test
    public void testSizeOnlyAdd() {
        Trie tr = new Trie();
        assertEquals(0, tr.size());
        tr.add("boooom");
        assertEquals(1, tr.size());
        tr.add("boooom");
        assertEquals(1, tr.size());
        tr.add("bom");
        assertEquals(2, tr.size());
        tr.add("aba");
        assertEquals(3, tr.size());
    }

    @Test
    public void testSizeAfterRemove() {
        Trie tr = new Trie();
        tr.add("boooom");
        tr.add("bom");
        tr.add("aba");
        assertEquals(3, tr.size());
        tr.remove("boooom");
        assertEquals(2, tr.size());
        tr.remove("boooom");
        assertEquals(2, tr.size());
        tr.add("abac");
        assertEquals(3, tr.size());
    }

    @Test
    public void testHowManyStartsWithPrefixOnlyAdd() {
        Trie tr = new Trie();
        assertEquals(0, tr.howManyStartsWithPrefix("a"));
        assertEquals(0, tr.howManyStartsWithPrefix("ab"));
        assertEquals(0, tr.howManyStartsWithPrefix("abc"));
        tr.add("a");
        assertEquals(1, tr.howManyStartsWithPrefix("a"));
        assertEquals(0, tr.howManyStartsWithPrefix("ab"));
        assertEquals(0, tr.howManyStartsWithPrefix("abc"));
        tr.add("ab");
        assertEquals(2, tr.howManyStartsWithPrefix("a"));
        assertEquals(1, tr.howManyStartsWithPrefix("ab"));
        assertEquals(0, tr.howManyStartsWithPrefix("abc"));
        tr.add("abc");
        assertEquals(3, tr.howManyStartsWithPrefix("a"));
        assertEquals(2, tr.howManyStartsWithPrefix("ab"));
        assertEquals(1, tr.howManyStartsWithPrefix("abc"));
    }

    @Test
    public void testHowManyStartsWithPrefixAfterRemove() {
        Trie tr = new Trie();
        tr.add("a");
        tr.add("ab");
        tr.add("abc");
        tr.remove("ab");
        assertEquals(2, tr.howManyStartsWithPrefix("a"));
        assertEquals(1, tr.howManyStartsWithPrefix("ab"));
        assertEquals(1, tr.howManyStartsWithPrefix("abc"));
        tr.add("aec");
        assertEquals(3, tr.howManyStartsWithPrefix("a"));
        assertEquals(1, tr.howManyStartsWithPrefix("ab"));
        assertEquals(1, tr.howManyStartsWithPrefix("abc"));
    }

    @Test
    public void testSerializeSimilarStrings() throws Exception {
        Trie t = new Trie();
        t.add("aaaa");
        t.add("aaab");
        t.add("aaac");
        t.add("aaad");

        ByteArrayOutputStream baoscopy = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        t.serialize(baos);
        baoscopy = baos;

        ByteArrayInputStream bais = new ByteArrayInputStream(baoscopy.toByteArray());
        t.deserialize(bais);
        bais.close();
        baos.close();

        assertEquals(4, t.size());
        assertTrue(t.contains("aaaa"));
        assertTrue(t.contains("aaab"));
        assertTrue(t.contains("aaac"));
        assertTrue(t.contains("aaad"));
    }

    @Test
    public void testDeserializeSimilarStrings() throws Exception {
        Trie t = new Trie();
        t.add("aaaaa");
        t.add("aaaab");
        t.add("aaaac");
        t.add("aaaad");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        t.serialize(baos);
        baos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        t.deserialize(bais);
        bais.close();

        assertEquals(4, t.size());
        assertTrue(t.contains("aaaaa"));
        assertTrue(t.contains("aaaab"));
        assertTrue(t.contains("aaaac"));
        assertTrue(t.contains("aaaad"));
    }
}