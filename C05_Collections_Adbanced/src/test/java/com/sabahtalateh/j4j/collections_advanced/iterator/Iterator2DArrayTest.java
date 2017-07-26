package com.sabahtalateh.j4j.collections_advanced.iterator;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Iterator2DArrayTest.
 */
public class Iterator2DArrayTest {
    @Test
    public void hasNextOnEmptyReturnFalse() throws Exception {
        assertThat(new Iterator2DArray(new int[][]{}).hasNext(), is(false));
        assertThat(new Iterator2DArray(new int[][]{{}}).hasNext(), is(false));
        assertThat(new Iterator2DArray(new int[][]{{}, {}}).hasNext(), is(false));
    }

    @Test
    public void nextElementCanBeRetrievedWhenIteratorContainsIt() throws Exception {
        Iterator2DArray iterator = new Iterator2DArray(new int[][]{{1}, {2, 3}, {4, 8}});
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(8));
        assertThat(iterator.hasNext(), is(false));
    }
}
