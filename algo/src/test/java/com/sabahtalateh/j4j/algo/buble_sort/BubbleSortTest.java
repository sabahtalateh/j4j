package com.sabahtalateh.j4j.algo.buble_sort;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * BubbleSortTest.
 */
public class BubbleSortTest {
    @Test
    public void testSort0() throws Exception {
        int[] array = {1};
        int[] expected = {1};

        assertThat(BubbleSort.sort(array), is(expected));
    }

    @Test
    public void testSort1() throws Exception {
        int[] array = {9, 8, 7, 6, 1, 5, 2, 3, 4};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        assertThat(BubbleSort.sort(array), is(expected));
    }

    @Test
    public void testSort2() throws Exception {
        int[] array = {9, 1, 3, 8, 5, 6, 7, 2, 4};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        assertThat(BubbleSort.sort(array), is(expected));
    }
}