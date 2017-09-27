package ru.spbau.mit.tukh.hw02.test;

import org.junit.Test;
import ru.spbau.mit.tukh.hw02.Matrix.*;

import static org.junit.Assert.*;

public class MatrixTest {
    @Test
    public void testSort() {
        int a[][] = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = (2 - j) + 3 * i;
            }
        }

        Matrix mt = new Matrix(a);
        mt.sort();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = j + 3 * i;
            }
        }

        assertArrayEquals(a, mt.toMatrix());
    }

    @Test
    public void testSetElement() {
        Matrix mt = new Matrix(2);
        mt.setElement(0, 0, 1);
        mt.setElement(0, 1, 2);
        mt.setElement(1, 0, 3);
        mt.setElement(1, 1, 4);
        int[][] a = {{1, 2}, {3, 4}};

        assertArrayEquals(a, mt.toMatrix());
    }

    @Test
    public void getSpiral() {
        int[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix mt = new Matrix(a);
        assertEquals("5 4 1 2 3 6 9 8 7 ", mt.getSpiral());

        int[][] b = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        Matrix mt2 = new Matrix(b);
        assertEquals("11 10 6 7 8 12 16 15 14 13 9 5 1 2 3 4 ", mt2.getSpiral());
    }

    @Test
    public void getElemet() {
        int[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix mt = new Matrix(a);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(i * 3 + j + 1, mt.getElemet(i, j));
            }
        }

        Matrix mt2 = new Matrix(1);
        mt2.setElement(0, 0, 239);
        assertEquals(239, mt2.getElemet(0, 0));
    }

    @Test
    public void toMatrix() {
        int[][] a = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = 239 % (i + j + 1);
            }
        }

        Matrix mt = new Matrix(a);
        assertArrayEquals(a, mt.toMatrix());
    }
}