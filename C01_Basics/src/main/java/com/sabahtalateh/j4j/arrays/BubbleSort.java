package com.sabahtalateh.j4j.arrays;

/**
 * BubbleSort.
 */
public class BubbleSort {
    /**
     * @param array to sort.
     * @return sorted array.
     */
    public int[] sort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }

        return array;
    }
}
