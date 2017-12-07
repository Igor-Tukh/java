package ru.spbau.mit.tukh.hw05.test;

import ru.spbau.mit.tukh.hw05.Maybe.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaybeTest {
    @Test(expected = NothingException.class)
    public void testJustNothing() throws Exception, NothingException {
        Maybe<Integer> nothing = Maybe.nothing();
        nothing.get();
    }

    @Test
    public void testJust() throws Exception, NothingException {
        Maybe<Integer> maybe = Maybe.just(239);
        assertEquals(new Integer(239), maybe.get());
    }

    @Test
    public void testIsPresentNothing() throws Exception {
        Maybe<Integer> nothing = Maybe.nothing();
        assertFalse(nothing.isPresent());
    }

    @Test
    public void testGet() throws Exception, NothingException {
        Maybe<Integer> maybe1 = Maybe.just(239);
        Maybe<String> maybe2 = Maybe.just("239");
        assertEquals(new Integer(239), maybe1.get());
        assertEquals("239", maybe2.get());
    }

    @Test
    public void testIsPresent() throws Exception {
        Maybe<Integer> mb1 = Maybe.just(239);
        Maybe<Integer> mb2 = Maybe.nothing();
        assertTrue(mb1.isPresent());
        assertFalse(mb2.isPresent());
    }

    @Test
    public void testNothing() throws Exception, NothingException {
        Maybe<Integer> maybe = Maybe.just(239);
        assertEquals("239", maybe.map(Object::toString).get());
    }

    @Test
    public void testMapNothing() throws Exception, NothingException {
        Maybe<Integer> maybe = Maybe.nothing();
        assertFalse(maybe.map(Object::toString).isPresent());
    }
}