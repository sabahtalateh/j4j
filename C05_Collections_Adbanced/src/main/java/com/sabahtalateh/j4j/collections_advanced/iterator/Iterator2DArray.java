package com.sabahtalateh.j4j.collections_advanced.iterator;

import java.util.Iterator;

/**
 * Iterator2DArray.
 */
public class Iterator2DArray implements Iterator<Integer> {

    private int[][] array;

    private int rowPos = 0;
    private int colPos = 0;

    /**
     * @param array array of arrays.
     */
    public Iterator2DArray(int[][] array) {
        this.array = array;
    }

    /**
     * @return true if has next, false if not.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        int colPosInRow = colPos;

        for (int i = rowPos; i < array.length; i++) {
            if (array[i].length - colPosInRow > 0) {
                result = true;
                break;
            }
            colPosInRow = 0;
        }
        return result;
    }

    /**
     * @return next element.
     */
    @Override
    public Integer next() {
        Integer next = null;

        for (int i = rowPos; i < array.length; ++i) {
            this.rowPos = i;
            if (array[i].length - this.colPos > 0) {
                next = array[i][this.colPos++];
                break;
            }
            this.colPos = 0;
        }
        return next;
    }
}
