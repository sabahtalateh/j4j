package com.sabahtalateh.j4j.algo.merge_sort;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * MergeSortTest.
 */
public class MergeSortTest {
    @Test
    public void test() throws Exception {
        int[] array = {5, 3, 2, 4, 8, 6, 7, 1, 9, 0};

        int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        assertThat(MergeSort.sort(array), is(expected));
    }
}
