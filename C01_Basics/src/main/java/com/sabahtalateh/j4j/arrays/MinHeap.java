package com.sabahtalateh.j4j.arrays;

/**
 * MinHeap.
 */
public class MinHeap {
    /**
     * Total element in heap.
     */
    private static int total;

    /**
     * Swap `a` and `b` in `array`.
     *
     * @param array where to swap
     * @param a     index
     * @param b     index
     */
    private void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    /**
     * Heapify `current` element of `array`.
     *
     * @param array   array
     * @param current element
     */
    private void heapify(int[] array, int current) {
        int left = current * 2;
        int right = left + 1;
        int smallest = current;

        if (left <= total && array[left] <= array[smallest]) {
            smallest = left;
        }
        if (right <= total && array[right] <= array[smallest]) {
            smallest = right;
        }
        if (smallest != current) {
            this.swap(array, current, smallest);
            heapify(array, smallest);
        }
    }

    /**
     * Heapify whole array.
     *
     * @param array whole array.
     * @return min heap on `array`.
     */
    public int[] heapifyAll(int[] array) {
        total = array.length - 1;

        for (int i = total / 2; i >= 0; i--) {
            heapify(array, i);
        }

        return array;
    }

    /**
     * Extract minimum from heap.
     *
     * @param heap heap
     * @return minimum element in heap.
     * @throws Exception if no more elements left.
     */
    public int extractMin(int[] heap) throws Exception {
        if (total < 0) {
            throw new Exception("No more elements");
        }

        int min = heap[0];
        swap(heap, 0, total);
        total--;
        heapify(heap, 0);
        return min;
    }
}
