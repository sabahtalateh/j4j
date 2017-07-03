package com.sabahtalateh.j4j.arrays;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests.
 */
public class BubbleSortTest {
    @Test
    public void testSort() {
        BubbleSort bubbleSort = new BubbleSort();

        assertThat(bubbleSort.sort(new int[]{}), is(new int[]{}));
        assertThat(bubbleSort.sort(new int[]{1}), is(new int[]{1}));

        assertThat(bubbleSort.sort(new int[]{1, 2}), is(new int[]{1, 2}));
        assertThat(bubbleSort.sort(new int[]{2, 1}), is(new int[]{1, 2}));

        assertThat(bubbleSort.sort(new int[]{1, 1}), is(new int[]{1, 1}));

        assertThat(
                bubbleSort.sort(new int[]{5, 3, -11, 13, 22, -100, Integer.MAX_VALUE, Integer.MIN_VALUE, 97}),
                is(new int[]{Integer.MIN_VALUE, -100, -11, 3, 5, 13, 22, 97, Integer.MAX_VALUE})
        );

    }
}