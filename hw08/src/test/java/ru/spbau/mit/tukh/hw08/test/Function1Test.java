package ru.spbau.mit.tukh.hw08.test;

import org.junit.Test;
import ru.spbau.mit.tukh.hw08.Collections.Function1;

import static org.junit.Assert.*;

public class Function1Test {
    @Test
    public void testApplyLength() throws Exception {
        Function1<String, Integer> len = String::length;
        assertEquals(Integer.valueOf(3), len.apply("239"));
        assertEquals(Integer.valueOf(7), len.apply("abacaba"));
    }

    @Test
    public void testApplySum() throws Exception {
        Function1<Integer, Integer> sum = (Integer x) -> x + 5;
        assertEquals(Integer.valueOf(244), sum.apply(239));
        assertEquals(Integer.valueOf(249), sum.compose(sum).apply(239));
    }

    @Test
    public void testComposeSumAndLength() throws Exception {
        Function1<String, Integer> len = String::length;
        Function1<Integer, Integer> sum = (Integer x) -> x + 5;
        assertEquals(Integer.valueOf(8), len.compose(sum).apply("239"));
        assertEquals(Integer.valueOf(7), len.compose(sum).apply("AU"));
    }

    @Test
    public void testComposeSumAndMultiply() throws Exception {
        Function1<Integer, Integer> mul = (Integer x) -> x * 5;
        Function1<Integer, Integer> sum = (Integer x) -> x + 5;
        assertEquals(Integer.valueOf(1200), mul.compose(sum).apply(239));
        assertEquals(Integer.valueOf(1220), sum.compose(mul).apply(239));
    }
}