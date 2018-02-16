package com.sabahtalateh.j4j.algo.merge_sort;

import java.util.Arrays;

/**
 * MergeSort.
 */
public class MergeSort {
    /**
     * @param array array.
     * @return sorted array.
     */
    public static int[] sort(int[] array) {

        if (array.length <= 2) {
            if (array.length == 2) {
                return merge(
                        new int[]{array[0]},
                        new int[]{array[1]}
                );
            }

            if (array.length == 1) {
                return new int[]{array[0]};
            }

            if (array.length == 0) {
                return new int[]{};
            }
        }

        int middle = array.length / 2;

        int[] left = Arrays.copyOfRange(array, 0, middle);
        int[] right = Arrays.copyOfRange(array, middle, array.length);

        int[] sortedLeft = sort(left);
        int[] sortedRight = sort(right);

        int[] merged = merge(sortedLeft, sortedRight);
        return merged;
    }

    /**
     * @param left  left sorted.
     * @param right right sorted.
     * @return sorted.
     */
    private static int[] merge(int[] left, int[] right) {

        int leftIdx = 0;
        int rightIdx = 0;

        int[] result = new int[left.length + right.length];
        int resultIdx = 0;

        // While there are elements in one of arrays
        // taking next and put minimum in result.
        while (leftIdx < left.length && rightIdx < right.length) {
            int nextLeft = left[leftIdx];
            int nextRight = right[rightIdx];

            if (nextLeft < nextRight) {
                result[resultIdx++] = nextLeft;
                leftIdx++;
            } else {
                result[resultIdx++] = nextRight;
                rightIdx++;
            }
        }

        // If right array ended before the left
        // add all the elements from left at the end of result.
        if (leftIdx == left.length && rightIdx != right.length) {
            for (int i = rightIdx; i < right.length; i++) {
                result[resultIdx++] = right[i];
            }
        }

        // If left array ended before the left
        // add all the elements from right at the end of result.
        if (leftIdx != left.length && rightIdx == right.length) {
            for (int i = leftIdx; i < left.length; i++) {
                result[resultIdx++] = left[i];
            }
        }

        return result;
    }
}
