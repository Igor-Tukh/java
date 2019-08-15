package ru.spbau.mit.tukh.test;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import ru.spbau.mit.tukh.Calculator.Calculator;
import ru.spbau.mit.tukh.Calculator.Parser;
import ru.spbau.mit.tukh.Stack.Stack;
import ru.spbau.mit.tukh.Stack.StackImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CalculatorTest {
    @Mock
    private StackImpl<Integer> st = mock(StackImpl.class);
    private Calculator calc = new Calculator(st);

    @Test
    public void simpleCheckingGetValTest() throws Exception {
        Parser p = new Parser("((1-10)/3+(1*2+3*2)*(2-21/2)+3/2)*2-2");
        Stack<Integer> st = new StackImpl<>();
        Calculator calc = new Calculator(st);
        assertEquals(Integer.valueOf(-134), calc.getVal(p.toPolish()));
    }

    @Test
    public void getValSimpleExpressionMockTest() throws Exception {
        when(st.pop()).thenReturn(1).thenReturn(1).thenReturn(2);
        assertEquals(Integer.valueOf(2), calc.getVal("1 1 + "));
        InOrder inOrder = inOrder(st);
        inOrder.verify(st, times(2)).push(1);
        inOrder.verify(st, times(2)).pop();
        inOrder.verify(st).push(2);
        inOrder.verify(st).pop();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void getValLessSimpleExpressionMockTest() throws Exception {
        when(st.pop()).thenReturn(239).thenReturn(239).thenReturn(1).thenReturn(478).thenReturn(477);
        assertEquals(Integer.valueOf(477), calc.getVal("239 239 + 1 - "));
        InOrder inOrder = inOrder(st);
        inOrder.verify(st, times(2)).push(239);
        inOrder.verify(st, times(2)).pop();
        inOrder.verify(st).push(478);
        inOrder.verify(st).push(1);
        inOrder.verify(st, times(2)).pop();
        inOrder.verify(st).push(477);
        inOrder.verify(st).pop();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void getValExpressionMockTest() throws Exception {
        when(st.pop()).thenReturn(2).thenReturn(1).thenReturn(3).thenReturn(3).thenReturn(4).thenReturn(9).thenReturn(2);
        when(st.isEmpty()).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false)
                .thenReturn(false).thenReturn(false).thenReturn(true);
        assertEquals(Integer.valueOf(2), calc.getVal("1 2 + 3 * 4 /"));
        InOrder inOrder = inOrder(st);
        inOrder.verify(st, times(1)).push(1);
        inOrder.verify(st, times(1)).push(2);
        inOrder.verify(st, times(2)).pop();
        inOrder.verify(st, times(2)).push(3);
        inOrder.verify(st, times(2)).pop();
        inOrder.verify(st).push(9);
        inOrder.verify(st).push(4);
        inOrder.verify(st, times(2)).pop();
        inOrder.verify(st).push(2);
        inOrder.verify(st).pop();
        inOrder.verifyNoMoreInteractions();
    }
}