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
        for (int i = rowPos; i < array.length; i++) {
            for (int j = colPos; j < array[rowPos].length; j++) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return next element.
     */
    @Override
    public Integer next() {
        int result = array[rowPos][colPos++];
        if (array[rowPos].length <= colPos) {
            colPos = 0;
            rowPos++;
        }
        return result;
    }
}
