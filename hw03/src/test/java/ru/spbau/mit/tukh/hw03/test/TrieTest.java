package ru.spbau.mit.tukh.hw03.test;

import ru.spbau.mit.tukh.hw03.Trie.*;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class TrieTest {
    @Test
    public void add() {
        Trie tr = new Trie();
        assertEquals(true, tr.add("it"));
        assertEquals(true, tr.add("is"));
        assertEquals(true, tr.add("bor"));
        assertEquals(false, tr.add("bor"));
        assertEquals(false, tr.add("is"));
        tr.remove("is");
        assertEquals(true, tr.add("is"));
    }

    @Test
    public void contains() {
        Trie tr = new Trie();
        assertEquals(false, tr.contains("aba"));
        assertEquals(false, tr.contains("abacabadabacabaeabacabadabacaba"));
        tr.add("aba");
        tr.add("abacabadabacabaeabacabadabacaba");
        assertEquals(true, tr.contains("aba"));
        assertEquals(true, tr.contains("abacabadabacabaeabacabadabacaba"));
        tr.remove("aba");
        assertEquals(false, tr.contains("aba"));
        tr.add("aba");
        assertEquals(true, tr.contains("aba"));
    }

    @Test
    public void remove() {
        Trie tr = new Trie();
        tr.add("expected");
        tr.add("ex");
        assertEquals(true, tr.remove("ex"));
        assertEquals(false, tr.remove("ex"));
        assertEquals(true, tr.remove("expected"));
    }

    @Test
    public void size() {
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
        tr.remove("boooom");
        assertEquals(2, tr.size());
        tr.remove("boooom");
        assertEquals(2, tr.size());
        tr.add("abac");
        assertEquals(3, tr.size());
    }

    @Test
    public void howManyStartsWithPrefix() {
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
    public void serialize() throws Exception {
        Trie t = new Trie();
        t.add("aaaa");
        t.add("aaab");
        t.add("aaac");
        t.add("aaad");

        ByteArrayOutputStream baoscopy = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            t.serialize(baos);
            baoscopy = baos;
        } catch (Throwable e) {
            System.err.println("Error during the serialization.");
            System.exit(1);
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(baoscopy.toByteArray());
        t.deserialize(bais);
        bais.close();

        assertEquals(4, t.size());
        assertEquals(true, t.contains("aaaa"));
        assertEquals(true, t.contains("aaab"));
        assertEquals(true, t.contains("aaac"));
        assertEquals(true, t.contains("aaad"));
    }

    @Test
    public void deserialize() throws Exception {
        Trie t = new Trie();
        t.add("aaaaa");
        t.add("aaaab");
        t.add("aaaac");
        t.add("aaaad");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        t.serialize(baos);
        baos.close();

        try (ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray())) {
            t.deserialize(bais);
        } catch (Throwable e) {
            System.err.println("Error during the deserialization.");
            System.exit(1);
        }

        assertEquals(4, t.size());
        assertEquals(true, t.contains("aaaaa"));
        assertEquals(true, t.contains("aaaab"));
        assertEquals(true, t.contains("aaaac"));
        assertEquals(true, t.contains("aaaad"));
    }
}