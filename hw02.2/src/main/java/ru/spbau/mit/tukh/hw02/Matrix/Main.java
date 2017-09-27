package ru.spbau.mit.tukh.hw02.Matrix;

import java.util.Random;

public class Main {
    public static void main(String args[]) {
        /*
        Usage example.
         */

        int[][] a = {{10, 9, 8, 7}, {1, 2, 3, 4}, {2, 3, 9, 0}, {5, 6, 6, 1}};
        Matrix ma = new Matrix(a);
        ma.sort();
        ma.printSpiral();
        int[][] b = ma.toMatrix();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\n---------\n");

        Matrix m = new Matrix(11);

        Random rnd = new Random();
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                m.setElement(i, j, Math.abs(rnd.nextInt()) % 9);
            }
        }

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(m.getElemet(i, j) + " ");
            }
            System.out.println();
        }
        System.out.println();

        m.sort();
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(m.getElemet(i, j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
