package com.sabahtalateh.j4j.arrays;

/**
 * Turn.
 */
public class Turn {
    /**
     * @param array to revert.
     * @return reverse array.
     */
    public int[] revert(int[] array) {
        for (int inHead = 0; inHead < array.length / 2; inHead++) {
            int inTail = array.length - inHead - 1;
            int tmp = array[inHead];
            array[inHead] = array[inTail];
            array[inTail] = tmp;
        }

        return array;
    }
}
