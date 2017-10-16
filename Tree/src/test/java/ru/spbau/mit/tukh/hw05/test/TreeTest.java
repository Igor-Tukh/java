package ru.spbau.mit.tukh.hw05.test;

import ru.spbau.mit.tukh.hw05.Tree.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TreeTest{
    @Test
    public void testAddInt() throws Exception{
        Tree<Integer> treeInt = createInt();
        for(int i = 0; i < 15; i++) {
            assertTrue(treeInt.contains(i));
        }
        assertFalse(treeInt.contains(-1));
        assertFalse(treeInt.contains(15));
        assertTrue(treeInt.add(-1));
        assertFalse(treeInt.add(0));
    }

    @Test
    public void testAddMyClass() throws Exception{
        Tree<myClass> treeInt = createMyClass();
        for(int i = 0; i < 15; i++) {
            assertTrue(treeInt.contains(new myClass(i / 3, i % 3)));
        }
        assertFalse(treeInt.contains(new myClass(-1, -1)));
        assertFalse(treeInt.contains(new myClass(5, 0)));
        assertTrue(treeInt.add(new myClass(-1, -1)));
        assertFalse(treeInt.add(new myClass(0, 0)));
    }

    @Test
    public void testMyClassSize() throws Exception{
        Tree<myClass> tr = new Tree<>();
        assertEquals(0, tr.size());
        tr.add(new myClass(-1, -1));
        assertEquals(1, tr.size());
        tr.add(new myClass(-1, -1));
        assertEquals(1, tr.size());
        tr = createMyClass();
        assertEquals(15, tr.size());
    }

    @Test
    public void testIntegerSize() throws Exception{
        Tree<Integer> tr = new Tree<>();
        assertEquals(0, tr.size());
        tr.add(-1);
        assertEquals(1, tr.size());
        tr.add(-1);
        assertEquals(1, tr.size());
        tr = createInt();
        assertEquals(15, tr.size());
    }

    @Test
    public void testContainsInt() throws Exception{
        Tree<Integer> tr = new Tree<>();
        assertFalse(tr.contains(0));
        tr.add(0);
        assertTrue(tr.contains(0));
        tr.add(0);
        assertTrue(tr.contains(0));
    }

    @Test
    public void testContainsMyClass() throws Exception{
        Tree<myClass> tr = new Tree<>();
        assertFalse(tr.contains(new myClass(0,0)));
        tr.add(new myClass(0,0));
        assertTrue(tr.contains(new myClass(0,0)));
        tr.add(new myClass(0,0));
        assertTrue(tr.contains(new myClass(0,0)));
    }

    private Tree<Integer> createInt(){
        Tree<Integer> tr = new Tree<>();
        for(int i = 0; i < 15; i++){
            tr.add(i);
        }
        tr.add(0);
        return tr;
    }

    private Tree<myClass> createMyClass(){
        Tree<myClass> tr = new Tree<>();
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 3; j++){
                tr.add(new myClass(i, j));
            }
        }
        tr.add(new myClass(0, 0));
        return tr;
    }

    private class myClass implements Comparable<myClass>{
        private int x, y;

        myClass(int xval, int yval){
            x = xval;
            y = yval;
        }

        @Override
        public int compareTo(myClass myClass){
            if (this.x == myClass.x){
                return this.y - myClass.y;
            } else {
                return this.x - myClass.x;
            }
        }
    }

}