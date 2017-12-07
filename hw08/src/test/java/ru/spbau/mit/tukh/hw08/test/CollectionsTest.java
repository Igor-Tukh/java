package ru.spbau.mit.tukh.hw08.test;

import org.junit.Test;
import ru.spbau.mit.tukh.hw08.Collections.*;
import ru.spbau.mit.tukh.hw08.Collections.Function1;
import ru.spbau.mit.tukh.hw08.Collections.Function2;
import ru.spbau.mit.tukh.hw08.Collections.Predicate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CollectionsTest {
    @Test
    public void testMapAdd5() throws Exception {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(2);
        al.add(3);
        al.add(9);
        Function1<Integer, Integer> sum = (Integer x) -> x + 5;
        List<Integer> ans = new ArrayList<>();
        ans.add(7);
        ans.add(8);
        ans.add(14);
        assertEquals(ans, Collections.map(sum, al));
    }

    @Test
    public void testMapStrToLength() throws Exception {
        ArrayList<String> al = new ArrayList<>();
        al.add("2");
        al.add("23");
        al.add("239");
        Function1<String, Integer> len = String::length;
        List<Integer> ans = new ArrayList<>();
        ans.add(1);
        ans.add(2);
        ans.add(3);
        assertEquals(ans, Collections.map(len, al));
    }

    @Test
    public void testFilterOdd() throws Exception {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(4);
        Predicate<Integer> odd = (Integer x) -> x % 2 == 1;
        List<Integer> ans = new ArrayList<>();
        ans.add(1);
        ans.add(3);
        assertEquals(ans, Collections.filter(odd, al));
    }

    @Test
    public void testFilterEven() throws Exception {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(4);
        Predicate<Integer> even = (Integer x) -> x % 2 == 0;
        List<Integer> ans = new ArrayList<>();
        ans.add(2);
        ans.add(4);
        assertEquals(ans, Collections.filter(even, al));
    }

    @Test
    public void testTakeWhileOdd() throws Exception {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(3);
        al.add(2);
        al.add(4);
        Predicate<Integer> odd = (Integer x) -> x % 2 == 1;
        List<Integer> ans = new ArrayList<>();
        ans.add(1);
        ans.add(3);
        assertEquals(ans, Collections.takeWhile(odd, al));
    }

    @Test
    public void testTakeWhileEven() throws Exception {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(2);
        al.add(4);
        al.add(1);
        al.add(3);
        Predicate<Integer> even = (Integer x) -> x % 2 == 0;
        List<Integer> ans = new ArrayList<>();
        ans.add(2);
        ans.add(4);
        assertEquals(ans, Collections.takeWhile(even, al));
    }

    @Test
    public void testTakeUnlessOddLength() throws Exception {
        ArrayList<String> al = new ArrayList<>();
        al.add("23");
        al.add("2392");
        al.add("2");
        al.add("239");
        Predicate<String> odd = (String s) -> s.length() % 2 == 1;
        List<String> ans = new ArrayList<>();
        ans.add("23");
        ans.add("2392");
        assertEquals(ans, Collections.takeUnless(odd, al));
    }

    @Test
    public void testTakeUnlessEvenLength() throws Exception {
        ArrayList<String> al = new ArrayList<>();
        al.add("2");
        al.add("239");
        al.add("23");
        al.add("2392");
        Predicate<String> even = (String s) -> s.length() % 2 == 0;
        List<String> ans = new ArrayList<>();
        ans.add("2");
        ans.add("239");
        assertEquals(ans, Collections.takeUnless(even, al));
    }

    @Test
    public void testFoldlSubtract() throws Exception {
        Function2<Integer, Integer, Integer> sub = (Integer x, Integer y) -> x - y;
        ArrayList<Integer> al = new ArrayList<>();
        al.add(4);
        al.add(3);
        al.add(2);
        al.add(1);
        assertEquals(Integer.valueOf(1), Collections.foldl(sub, 11, al));
    }

    @Test
    public void testFoldlDivide() throws Exception {
        Function2<Integer, Integer, Integer> div = (Integer x, Integer y) -> y / x;
        ArrayList<Integer> al = new ArrayList<>();
        al.add(16);
        al.add(4);
        assertEquals(Integer.valueOf(1), Collections.foldl(div, 4, al));
    }

    @Test
    public void testFoldrSubtract() throws Exception {
        Function2<Integer, Integer, Integer> sub = (Integer x, Integer y) -> y - x;
        ArrayList<Integer> al = new ArrayList<>();
        al.add(4);
        al.add(3);
        al.add(2);
        al.add(1);
        assertEquals(Integer.valueOf(-10), Collections.foldr(sub, 0, al));
    }

    @Test
    public void testFoldrDivide() throws Exception {
        Function2<Integer, Integer, Integer> div = (Integer x, Integer y) -> x / y;
        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(8);
        al.add(64);
        assertEquals(Integer.valueOf(1), Collections.foldr(div, 8, al));
    }

}