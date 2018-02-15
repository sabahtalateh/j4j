package com.sabahtalateh.j4j.algo.buble_sort;

/**
 * BubbleSort.
 */
public class BubbleSort {
    /**
     * @param array array.
     * @return sorted.
     */
    public static int[] sort(int[] array) {

        for (int j = 0; j < array.length - 1; j++) {
            for (int i = 0; i < array.length - j - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int tmp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tmp;
                }
            }
        }

        return array;
    }
}
