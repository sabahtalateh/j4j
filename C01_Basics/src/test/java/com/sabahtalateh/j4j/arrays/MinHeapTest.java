package com.sabahtalateh.j4j.arrays;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests.
 */
public class MinHeapTest {
    @Test
    public void testHeap() throws Exception {
        MinHeap minHeap = new MinHeap();

        int[] array = new int[]{3, 6, 1, 5, 4, 7, 9, 10, 2, 4, 8};
        int[] heap = minHeap.heapifyAll(array);

        assertThat(minHeap.extractMin(heap), is(1));
        assertThat(minHeap.extractMin(heap), is(2));
        assertThat(minHeap.extractMin(heap), is(3));
        assertThat(minHeap.extractMin(heap), is(4));
        assertThat(minHeap.extractMin(heap), is(4));
        assertThat(minHeap.extractMin(heap), is(5));
        assertThat(minHeap.extractMin(heap), is(6));
        assertThat(minHeap.extractMin(heap), is(7));
        assertThat(minHeap.extractMin(heap), is(8));
        assertThat(minHeap.extractMin(heap), is(9));
        assertThat(minHeap.extractMin(heap), is(10));
    }
}
