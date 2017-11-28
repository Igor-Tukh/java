package ru.spbau.mit.tukh.test;

import org.junit.Test;
import ru.spbau.mit.tukh.Calculator.Parser;

import static org.junit.Assert.*;

public class ParserTest {
    private Parser p;

    @Test
    public void toPolishSimpleTest() throws Exception {
        p = new Parser("1 + 1");
        assertEquals("1  1 +", p.toPolish());
    }

    @Test
    public void getIntTest() throws Exception {
        p = new Parser("239+");
        assertEquals(Integer.valueOf(239), p.getInt());
        assertNull(p.getInt());
    }

    @Test
    public void getOperatorTest() throws Exception {
        p = new Parser("+-*/");
        assertEquals('+', (char) p.getOperator().getSymbol());
        assertEquals('-', (char) p.getOperator().getSymbol());
        assertEquals('*', (char) p.getOperator().getSymbol());
        assertEquals('/', (char) p.getOperator().getSymbol());
    }

    @Test
    public void hasMoreTokens() throws Exception {
        p = new Parser("239+");
        assertTrue(p.hasMoreTokens());
        p.getInt();
        assertTrue(p.hasMoreTokens());
        p.getOperator();
        assertFalse(p.hasMoreTokens());
    }

}