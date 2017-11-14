package ru.spbau.mit.tukh.hw07.test;

import org.junit.Test;
import ru.spbau.mit.tukh.hw07.MyTreeSet;
import ru.spbau.mit.tukh.hw07.MyTreeSetImpl;

import java.util.Comparator;
import java.util.Iterator;

import static org.junit.Assert.*;

public class MyTreeSetImplTest {
    @Test
    public void testAddSimple() throws Exception {
        MyTreeSetImpl<Integer> mtsi = new MyTreeSetImpl<>();
        assertTrue(mtsi.add(0));
        assertTrue(mtsi.add(1));
        assertTrue(mtsi.add(2));
    }

    @Test
    public void testAddDuplicates() throws Exception {
        MyTreeSetImpl<Integer> mtsi = new MyTreeSetImpl<>();
        assertTrue(mtsi.add(0));
        assertFalse(mtsi.add(0));
    }

    @Test
    public void remove() throws Exception {
    }

    @Test
    public void testIteratorSimple() throws Exception {
        MyTreeSetImpl<Integer> mtsi = new MyTreeSetImpl<>();
        mtsi.add(0);
        mtsi.add(1);
        mtsi.add(2);
        int cur = 0;
        for (Iterator<Integer> it = mtsi.iterator(); it.hasNext(); cur++) {
            assertEquals(Integer.valueOf(cur), it.next());
        }
    }

    @Test
    public void testIteratorDuplicates() throws Exception {
        MyTreeSetImpl<Integer> mtsi = new MyTreeSetImpl<>();
        mtsi.add(0);
        mtsi.add(0);
        mtsi.add(0);
        int cur = 0;
        for (Iterator<Integer> it = mtsi.iterator(); it.hasNext(); cur++) {
            assertEquals(Integer.valueOf(cur), it.next());
        }
    }

    @Test
    public void testIteratorManyNumbersAndStrangeOrder() throws Exception {
        MyTreeSetImpl<Integer> mtsi = new MyTreeSetImpl<>();
        mtsi.add(1);
        mtsi.add(0);
        mtsi.add(2);
        mtsi.add(-1);
        mtsi.add(3);
        mtsi.add(-2);
        int cur = -2;
        for (Iterator<Integer> it = mtsi.iterator(); it.hasNext(); cur++) {
            assertEquals(Integer.valueOf(cur), it.next());
        }
    }

    @Test
    public void testSizeSimple() throws Exception {
        MyTreeSetImpl<Integer> mtsi = new MyTreeSetImpl<>();
        assertEquals(0, mtsi.size());
        mtsi.add(1);
        assertEquals(1, mtsi.size());
        mtsi.add(0);
        assertEquals(2, mtsi.size());
        mtsi.add(2);
        assertEquals(3, mtsi.size());
        mtsi.add(-1);
        assertEquals(4, mtsi.size());
    }

    @Test
    public void testSizeDuplicates() throws Exception {
        MyTreeSetImpl<Integer> mtsi = new MyTreeSetImpl<>();
        assertEquals(0, mtsi.size());
        mtsi.add(1);
        assertEquals(1, mtsi.size());
        mtsi.add(1);
        assertEquals(1, mtsi.size());
        mtsi.add(2);
        assertEquals(2, mtsi.size());
        mtsi.add(2);
        assertEquals(2, mtsi.size());
    }

    @Test
    public void testDescendingIteratorSimple() throws Exception {
        MyTreeSetImpl<Integer> mtsi = new MyTreeSetImpl<>();
        mtsi.add(0);
        mtsi.add(1);
        mtsi.add(2);
        int cur = 2;
        for (Iterator<Integer> it = mtsi.descendingIterator(); it.hasNext(); cur--) {
            assertEquals(Integer.valueOf(cur), it.next());
        }
    }

    @Test
    public void testDescendingIteratorDuplicates() throws Exception {
        MyTreeSetImpl<Integer> mtsi = new MyTreeSetImpl<>();
        mtsi.add(0);
        mtsi.add(0);
        mtsi.add(0);
        int cur = 0;
        for (Iterator<Integer> it = mtsi.descendingIterator(); it.hasNext(); cur--) {
            assertEquals(Integer.valueOf(cur), it.next());
        }
    }

    @Test
    public void testDescendingIteratorManyNumbersAndStrangeOrder() throws Exception {
        MyTreeSetImpl<Integer> mtsi = new MyTreeSetImpl<>();
        mtsi.add(1);
        mtsi.add(0);
        mtsi.add(2);
        mtsi.add(-1);
        mtsi.add(3);
        mtsi.add(-2);
        int cur = 3;
        for (Iterator<Integer> it = mtsi.descendingIterator(); it.hasNext(); cur--) {
            assertEquals(Integer.valueOf(cur), it.next());
        }
    }

    @Test
    public void testDescendingSet() throws Exception {
        MyTreeSetImpl<Integer> mtsi = new MyTreeSetImpl<>();
        mtsi.add(1);
        mtsi.add(0);
        mtsi.add(2);
        mtsi.add(-1);
        mtsi.add(3);
        mtsi.add(-2);
        MyTreeSet<Integer> mtsid = mtsi.descendingSet();
        assertEquals(Integer.valueOf(-1), mtsid.lower(-2));
        assertEquals(Integer.valueOf(-1), mtsid.floor(-1));
        assertEquals(Integer.valueOf(-1), mtsid.ceiling(-1));
        assertEquals(Integer.valueOf(-1), mtsid.higher(0));
        assertEquals(mtsi.last(), mtsid.first());
        assertEquals(mtsi.first(), mtsid.last());
        int cur = -2;
        for (Iterator<Integer> it = mtsid.descendingIterator(); it.hasNext(); cur++) {
            assertEquals(Integer.valueOf(cur), it.next());
        }
    }

    @Test
    public void testFirst() throws Exception {
        MyTreeSetImpl<Integer> mtsi = new MyTreeSetImpl<>();
        mtsi.add(1);
        mtsi.add(0);
        mtsi.add(2);
        mtsi.add(-1);
        mtsi.add(3);
        mtsi.add(-2);
        assertEquals(Integer.valueOf(-2), mtsi.first());
    }

    @Test
    public void testLast() throws Exception {
        MyTreeSetImpl<Integer> mtsi = new MyTreeSetImpl<>();
        mtsi.add(1);
        mtsi.add(0);
        mtsi.add(2);
        mtsi.add(1);
        mtsi.add(0);
        assertEquals(Integer.valueOf(2), mtsi.last());
    }

    @Test
    public void testLowerManyCases() throws Exception {
        MyTreeSetImpl<Integer> mtsi = new MyTreeSetImpl<>();
        mtsi.add(1);
        mtsi.add(0);
        mtsi.add(2);
        mtsi.add(-1);
        mtsi.add(3);
        mtsi.add(-2);
        assertEquals(null, mtsi.lower(-2));
        assertEquals(Integer.valueOf(-2), mtsi.lower(-1));
        assertEquals(Integer.valueOf(-1), mtsi.lower(0));
        assertEquals(Integer.valueOf(0), mtsi.lower(1));
        assertEquals(Integer.valueOf(1), mtsi.lower(2));
        assertEquals(Integer.valueOf(2), mtsi.lower(3));
        assertEquals(Integer.valueOf(3), mtsi.lower(4));
    }

    @Test
    public void testFloorManyCases() throws Exception {
        MyTreeSetImpl<Integer> mtsi = new MyTreeSetImpl<>();
        mtsi.add(1);
        mtsi.add(0);
        mtsi.add(2);
        mtsi.add(-1);
        mtsi.add(3);
        mtsi.add(-2);
        assertEquals(null, mtsi.floor(-3));
        assertEquals(Integer.valueOf(-2), mtsi.floor(-2));
        assertEquals(Integer.valueOf(-1), mtsi.floor(-1));
        assertEquals(Integer.valueOf(0), mtsi.floor(0));
        assertEquals(Integer.valueOf(1), mtsi.floor(1));
        assertEquals(Integer.valueOf(2), mtsi.floor(2));
        assertEquals(Integer.valueOf(3), mtsi.floor(3));
        assertEquals(Integer.valueOf(3), mtsi.floor(4));
    }

    @Test
    public void testCeilingManyCases() throws Exception {
        MyTreeSetImpl<Integer> mtsi = new MyTreeSetImpl<>();
        mtsi.add(1);
        mtsi.add(0);
        mtsi.add(2);
        mtsi.add(-1);
        mtsi.add(3);
        mtsi.add(-2);
        assertEquals(Integer.valueOf(-2), mtsi.ceiling(-3));
        assertEquals(Integer.valueOf(-2), mtsi.ceiling(-2));
        assertEquals(Integer.valueOf(-1), mtsi.ceiling(-1));
        assertEquals(Integer.valueOf(0), mtsi.ceiling(0));
        assertEquals(Integer.valueOf(1), mtsi.ceiling(1));
        assertEquals(Integer.valueOf(2), mtsi.ceiling(2));
        assertEquals(Integer.valueOf(3), mtsi.ceiling(3));
        assertEquals(null, mtsi.ceiling(4));
    }

    @Test
    public void testHigherManyCases() throws Exception {
        MyTreeSetImpl<Integer> mtsi = new MyTreeSetImpl<>();
        mtsi.add(1);
        mtsi.add(0);
        mtsi.add(2);
        mtsi.add(-1);
        mtsi.add(3);
        mtsi.add(-2);
        assertEquals(Integer.valueOf(-2), mtsi.higher(-3));
        assertEquals(Integer.valueOf(-1), mtsi.higher(-2));
        assertEquals(Integer.valueOf(0), mtsi.higher(-1));
        assertEquals(Integer.valueOf(1), mtsi.higher(0));
        assertEquals(Integer.valueOf(2), mtsi.higher(1));
        assertEquals(Integer.valueOf(3), mtsi.higher(2));
        assertEquals(null, mtsi.higher(3));
        assertEquals(null, mtsi.higher(4));
    }

    @Test
    public void MyTreeSetTestPair() throws Exception {
        MyTreeSetImpl<Pair> mtsp = new MyTreeSetImpl<>();
        mtsp.add(new Pair(0, 1));
        mtsp.add(new Pair(1, 2));
        mtsp.add(new Pair(-1, 0));
        int cur = -1;
        for (Iterator<Pair> it = mtsp.iterator(); it.hasNext(); cur++) {
            assertEquals(0, (new Pair(cur, cur + 1)).compareTo(it.next()));
        }
    }

    @Test
    public void MyTreeSetTestTriple() throws Exception {
        Comparator<Triple> cmp = new Comparator<Triple>() {
            @Override
            public int compare(Triple t1, Triple t2) {
                if (t1.a == t2.a) {
                    if (t1.b == t2.b) {
                        return t1.c - t2.c;
                    } else {
                        return t1.b - t2.b;
                    }
                } else {
                    return t1.a - t2.a;
                }
            }
        };

        MyTreeSetImpl<Triple> mtsp = new MyTreeSetImpl<>(cmp);

        mtsp.add(new Triple(0, 1, 0));
        mtsp.add(new Triple(0, 1, 2));
        mtsp.add(new Triple(0, 1, 1));
        mtsp.add(new Triple(0, 1, -1));

        int cur = -1;
        for (Iterator<Triple> it = mtsp.iterator(); it.hasNext(); cur++) {
            assertEquals(0, cmp.compare(new Triple(0, 1, cur), it.next()));
        }
    }

    private class Pair implements Comparable<Pair> {
        private int x, y;

        Pair(int first, int second) {
            x = first;
            y = second;
        }

        @Override
        public int compareTo(Pair other) {
            if (this.x == other.x) {
                return this.y - other.y;
            } else {
                return this.x - other.x;
            }
        }
    }

    private class Triple {
        private int a, b, c;

        Triple(int first, int second, int third) {
            a = first;
            b = second;
            c = third;
        }
    }
}