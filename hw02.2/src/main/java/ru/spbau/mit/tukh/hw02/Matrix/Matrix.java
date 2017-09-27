package ru.spbau.mit.tukh.hw02.Matrix;

import java.util.Arrays;

/**
 * Matrix class. Contains square matrix consist of set of columns.
 */

public class Matrix {
    private Column[] columns;

    /**
     * Matrix constructor.
     *
     * @param size means that matrix is size x size.
     */

    public Matrix(int size) {
        columns = new Column[size];

        for (int i = 0; i < size; i++) {
            columns[i] = new Column(size);
        }
    }

    /**
     * Matrix constructor.
     *
     * @param matrix is square matrix contain elements of int type.
     */

    public Matrix(int[][] matrix) {
        int n = matrix.length;
        columns = new Column[n];

        for (int i = 0; i < n; i++) {
            columns[i] = new Column(n);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                columns[j].setElement(i, matrix[i][j]);
            }
        }
    }

    /**
     * Sort method. Columns of Matrix will be sorted by their first elements.
     */

    public void sort() {
        Arrays.sort(columns);
    }

    /**
     * setElement method. Use to set value equal to value param of Matrix elememt in row with number row param
     * and column with number column param.
     *
     * @param row    is rows number.
     * @param column is columns number.
     * @param value  is settable value.
     */

    public void setElement(int row, int column, int value) {
        columns[column].setElement(row, value);
    }

    /**
     * getSpiral method. Returns spiral walk of Matrix (from center).
     *
     * @return string consist of matrix elements at the walk.
     */

    public String getSpiral() {
        int size = columns.length;

        int curx = size / 2;
        int cury = size / 2;

        StringBuilder sb = new StringBuilder();

        int dx[] = {0, -1, 0, 1};
        int dy[] = {-1, 0, 1, 0};

        for (int i = 1; ; i += 2) {
            for (int dir = 0; dir < 4; dir++) {
                for (int j = 0; j < i + dir / 2; j++) {
                    sb.append(columns[cury].getElement(curx)).append(" ");
                    curx += dx[dir];
                    cury += dy[dir];

                    if (curx == -1 || cury == -1 || cury == size || curx == size) {
                        return sb.toString();
                    }
                }
            }
        }
    }

    /**
     * Prints spiral walk of Matrix (from center).
     */
    void printSpiral() {
        System.out.println(getSpiral());
    }

    /**
     * getElement method. Returns elements (row, column) value.
     *
     * @param row    is number of row.
     * @param column is number of columns.
     * @return element value.
     */

    public int getElemet(int row, int column) {
        return columns[column].getElement(row);
    }

    /**
     * toMatrix method. Returns matrix which contains in Matrix.
     *
     * @return square matrix.
     */

    public int[][] toMatrix() {
        int ans[][] = new int[columns.length][columns.length];

        for (int i = 0; i < columns.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                ans[i][j] = columns[j].getElement(i);
            }
        }

        return ans;
    }

    /**
     * Inner column class. Consist of integers (int). Implements comparable interface, compares by first elements.
     */

    private static class Column implements Comparable<Column> {
        private int[] elements;

        /**
         * Colunm constructor.
         *
         * @param height is number of elements in column.
         */

        Column(int height) {
            elements = new int[height];
        }

        /**
         * Comparator. Compares first elements of columns.
         *
         * @param column is column with which compares current one.
         * @return result of compare.
         */
        @Override
        public int compareTo(Column column) {
            return this.elements[0] - column.elements[0];
        }

        /**
         * getElement method. Returns element with given index.
         *
         * @param index is index of requesting element.
         * @return requesting element.
         */
        private int getElement(int index) {
            return elements[index];
        }

        /**
         * setElement method. Sets value of an element in a given row.
         *
         * @param row   is row number.
         * @param value is settable value.
         */
        private void setElement(int row, int value) {
            elements[row] = value;
        }
    }
}