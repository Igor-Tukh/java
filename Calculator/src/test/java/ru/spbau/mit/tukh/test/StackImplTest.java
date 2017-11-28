package ru.spbau.mit.tukh.test;

import org.junit.Test;
import ru.spbau.mit.tukh.Stack.Stack;
import ru.spbau.mit.tukh.Stack.StackImpl;

import static org.junit.Assert.*;

public class StackImplTest {
    private Stack<Integer> st = new StackImpl<>();

    @Test
    public void onlyOnePushTest() throws Exception {
        assertTrue(st.isEmpty());
        st.push(1);
        assertEquals(Integer.valueOf(1), st.front());
        assertFalse(st.isEmpty());
    }

    @Test
    public void pushAndPopTest() throws Exception {
        assertTrue(st.isEmpty());
        st.push(1);
        assertFalse(st.isEmpty());
        st.push(2);
        assertFalse(st.isEmpty());
        st.pop();
        assertFalse(st.isEmpty());
        st.pop();
        assertTrue(st.isEmpty());
    }

    @Test
    public void pushAndPopAndFrontTest() throws Exception {
        st.push(1);
        assertEquals(Integer.valueOf(1), st.front());
        st.push(2);
        assertEquals(Integer.valueOf(2), st.front());
        st.push(3);
        assertEquals(Integer.valueOf(3), st.front());
        st.pop();
        assertEquals(Integer.valueOf(2), st.front());
        st.pop();
        assertEquals(Integer.valueOf(1), st.front());
        st.pop();
    }

    @Test
    public void isEmptyBigTest() throws Exception {
        assertTrue(st.isEmpty());
        st.push(1);
        assertFalse(st.isEmpty());
        st.push(2);
        assertFalse(st.isEmpty());
        st.pop();
        st.push(3);
        assertFalse(st.isEmpty());
        st.pop();
        assertFalse(st.isEmpty());
        assertFalse(st.isEmpty());
        st.push(9);
        assertFalse(st.isEmpty());
        st.pop();
        st.push(3);
        assertFalse(st.isEmpty());
        st.pop();
        assertFalse(st.isEmpty());
        st.pop();
        assertTrue(st.isEmpty());
    }
}