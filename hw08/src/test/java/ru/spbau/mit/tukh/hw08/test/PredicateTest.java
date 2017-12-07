package ru.spbau.mit.tukh.hw08.test;

import org.junit.Test;
import ru.spbau.mit.tukh.hw08.Collections.Predicate;

import static org.junit.Assert.*;

public class PredicateTest {
    @Test
    public void testOrFalseAndFalse() throws Exception {
        Predicate<Integer> pr1 = (Integer x) -> x % 2 == 0;
        Predicate<Integer> pr2 = (Integer x) -> x % 3 == 1;
        assertFalse(pr1.or(pr2).apply(5));
    }

    @Test
    public void testOrFalseAndTrue() throws Exception {
        Predicate<Integer> pr1 = (Integer x) -> x % 2 == 0;
        Predicate<Integer> pr2 = (Integer x) -> x % 3 == 1;
        assertTrue(pr1.or(pr2).apply(1));
    }

    @Test
    public void testOrTrueAndFalse() throws Exception {
        Predicate<Integer> pr1 = (Integer x) -> x % 2 == 0;
        Predicate<Integer> pr2 = (Integer x) -> x % 3 == 1;
        assertTrue(pr1.or(pr2).apply(2));
    }

    @Test
    public void testOrTrueAndTrue() throws Exception {
        Predicate<Integer> pr1 = (Integer x) -> x % 2 == 0;
        Predicate<Integer> pr2 = (Integer x) -> x % 3 == 1;
        assertTrue(pr1.or(pr2).apply(4));
    }

    @Test
    public void testAndFalseAndFalse() throws Exception {
        Predicate<String> pr1 = (String s) -> s.length() % 2 == 0;
        Predicate<String> pr2 = (String s) -> s.length() % 3 == 1;
        assertFalse(pr1.and(pr2).apply("239"));
    }

    @Test
    public void testAndTrueAndFalse() throws Exception {
        Predicate<String> pr1 = (String s) -> s.length() % 2 == 0;
        Predicate<String> pr2 = (String s) -> s.length() % 3 == 1;
        assertFalse(pr1.and(pr2).apply("23"));
    }

    @Test
    public void testAndFalseAndTrue() throws Exception {
        Predicate<String> pr1 = (String s) -> s.length() % 2 == 0;
        Predicate<String> pr2 = (String s) -> s.length() % 3 == 1;
        assertFalse(pr1.and(pr2).apply("2"));
    }

    @Test
    public void testAndTrueAndTrue() throws Exception {
        Predicate<String> pr1 = (String s) -> s.length() % 2 == 0;
        Predicate<String> pr2 = (String s) -> s.length() % 3 == 1;
        assertTrue(pr1.and(pr2).apply("2392"));
    }

    @Test
    public void notAndFalseAndFalse() throws Exception {
        Predicate<String> pr1 = (String s) -> s.length() % 2 == 0;
        Predicate<String> pr2 = (String s) -> s.length() % 3 == 1;
        assertTrue(pr1.and(pr2).not().apply("239"));
    }

    @Test
    public void notAndFalseOrTrue() throws Exception {
        Predicate<String> pr1 = (String s) -> s.length() % 2 == 0;
        Predicate<String> pr2 = (String s) -> s.length() % 3 == 1;
        assertFalse(pr1.or(pr2).not().apply("2"));
    }

    @Test
    public void testALWAYS_TRUE() throws Exception {
        Predicate<String> t1 = Predicate.alwaysTrue();
        Predicate<Integer> t2 = Predicate.alwaysTrue();
        assertTrue(t1.apply("239"));
        assertTrue(t2.apply(239));
        assertTrue(t1.apply("0"));
        assertTrue(t2.apply(0));
    }

    @Test
    public void ALWAYS_FALSE() throws Exception {
        Predicate<String> f1 = Predicate.alwaysFalse();
        Predicate<Integer> f2 = Predicate.alwaysFalse();
        assertFalse(f1.apply("239"));
        assertFalse(f2.apply(239));
        assertFalse(f1.apply("0"));
        assertFalse(f2.apply(0));
    }
}