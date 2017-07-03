package com.sabahtalateh.j4j.max;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Let's test it out.
 */
public class MaxTest {

    @Test
    public void maxTwoNumbersTest() {
        Max max = new Max();

        assertThat(max.max(1, 1), is(1));
        assertThat(max.max(1, 0), is(1));
        assertThat(max.max(0, 1), is(1));
        assertThat(max.max(Integer.MAX_VALUE, Integer.MIN_VALUE), is(Integer.MAX_VALUE));
        assertThat(max.max(42, Integer.MIN_VALUE), is(42));
        assertThat(max.max(42, 43), is(43));
    }

    @Test
    public void maxThreeNumbersTest() {
        Max max = new Max();

        assertThat(max.max(1, 1, 1), is(1));
        assertThat(max.max(1, 2, 3), is(3));
        assertThat(max.max(1, 3, 2), is(3));
        assertThat(max.max(2, 1, 3), is(3));
        assertThat(max.max(2, 3, 1), is(3));
        assertThat(max.max(3, 1, 2), is(3));
        assertThat(max.max(3, 2, 1), is(3));
    }
}
