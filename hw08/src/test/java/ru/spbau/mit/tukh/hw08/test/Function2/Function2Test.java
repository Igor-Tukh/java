package ru.spbau.mit.tukh.hw08.test.Function2;

import org.junit.Test;
import ru.spbau.mit.tukh.hw08.Function.Function1.Function1;
import ru.spbau.mit.tukh.hw08.Function.Function2.Function2;

import static org.junit.Assert.*;

public class Function2Test {
    @Test
    public void testApplyLinear() throws Exception {
        Function2<Integer, Integer, Integer> lin = (Integer a, Integer b) -> 2 * a + 3 * b + 9;
        assertEquals(Integer.valueOf(14), lin.apply(1, 1));
        assertEquals(Integer.valueOf(19), lin.apply(2, 2));
    }

    @Test
    public void testApplySumLength() throws Exception {
        Function2<String, Integer, Integer> inc = (String s, Integer a) -> s.length() + a;
        assertEquals(Integer.valueOf(242), inc.apply("239", 239));
    }

    @Test
    public void testComposeLenAndSquare() throws Exception {
        Function2<String, Integer, Integer> inc = (String s, Integer a) -> s.length() + a;
        Function1<Integer, Integer> mysqr = (Integer x) -> x * x + 239;
        assertEquals(Integer.valueOf(248), inc.compose(mysqr).apply("239", 0));
    }

    @Test
    public void testComposeDoubleLength() throws Exception {
        Function1<Integer, Integer> sum = (Integer x) -> x + 5;
        Function2<String, String, Integer> inc = (String s1, String s2) -> s1.length() + s2.length();
        assertEquals(Integer.valueOf(15), inc.compose(sum).apply("239", "abacaba"));
    }

    @Test
    public void testBind1Linear() throws Exception {
        Function2<Integer, Integer, Integer> lin = (Integer x, Integer y) -> 2 * x + y;
        Function1<Integer, Integer> sum1 = lin.bind1(239);
        assertEquals(Integer.valueOf(478), sum1.apply(0));
    }

    @Test
    public void testBind1LengthAndSum() throws Exception {
        Function2<String, Integer, Integer> lin = (String s, Integer x) -> 2 * s.length() + x;
        Function1<Integer, Integer> sum1 = lin.bind1("239");
        assertEquals(Integer.valueOf(10), sum1.apply(4));
    }

    @Test
    public void testBind2Linear() throws Exception {
        Function2<Integer, Integer, Integer> lin = (Integer x, Integer y) -> 2 * x + y;
        Function1<Integer, Integer> sum1 = lin.bind2(239);
        assertEquals(Integer.valueOf(239), sum1.apply(0));
    }

    @Test
    public void testBind2LengthAndSum() throws Exception {
        Function2<String, Integer, Integer> lin = (String s, Integer x) -> 2 * s.length() + x;
        Function1<String, Integer> sum1 = lin.bind2(239);
        assertEquals(Integer.valueOf(245), sum1.apply("239"));
    }

    @Test
    public void testCurrySum5() throws Exception {
        Function2<Integer, Integer, Integer> sum = (Integer x, Integer y) -> 2 * x + y;
        assertEquals(Integer.valueOf(483), sum.curry(5).apply(239));
    }
}