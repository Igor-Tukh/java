package ru.spbau.mit.tukh.hw05.test;

import ru.spbau.mit.tukh.hw05.Maybe.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaybeTest {
    @Test
    public void testJust() throws Exception{
        Maybe<Integer> mb1 = Maybe.just(239);
        Maybe<Integer> mb2 = Maybe.nothing();
        boolean failed = false;

        try{
            assertEquals(new Integer(239), mb1.get());
        } catch (NothingException e){
            System.err.println("Something is wrong in just");
            System.exit(1);
        }

        try{
            mb2.get();
        } catch(NothingException e){
            failed = true;
        }

        assertTrue(failed);
    }

    @Test
    public void testNothing() throws Exception{
        Maybe<Integer> mb = Maybe.nothing();
        boolean failed = false;
        try{
            mb.get();
        } catch(NothingException e){
            failed = true;
        }
        assertTrue(failed);
        assertFalse(mb.isPresent());
    }

    @Test
    public void testGet() throws Exception{
        Maybe<Integer> mb1 = Maybe.just(239);
        Maybe<String> mb2 = Maybe.just("239");

        try{
            assertEquals(new Integer(239), mb1.get());
            assertEquals("239", mb2.get());
        } catch (NothingException e){
            System.err.println("Something is wrong in get");
            System.exit(1);
        }
    }

    @Test
    public void testIsPresent() throws Exception{
        Maybe<Integer> mb1 = Maybe.just(239);
        Maybe<Integer> mb2 = Maybe.nothing();
        assertTrue(mb1.isPresent());
        assertFalse(mb2.isPresent());
    }

    @Test
    public void mapTest() throws Exception {
        Maybe<Integer> mb1 = Maybe.just(239);
        try{
            assertEquals(mb1.map(Object::toString).get(), "239");
        } catch (NothingException e){
            System.err.println("Something is wrong in map.");
            System.exit(1);
        }
    }
}