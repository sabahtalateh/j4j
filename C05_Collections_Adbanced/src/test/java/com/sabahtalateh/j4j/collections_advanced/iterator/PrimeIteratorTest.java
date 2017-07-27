package com.sabahtalateh.j4j.collections_advanced.iterator;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * PrimeIteratorTest.
 */
public class PrimeIteratorTest {

    @Test
    public void ifNoNumbersInArrayThanHasNextReturnsFalse() {
        assertThat(new PrimeIterator(new int[]{}).hasNext(), is(false));
    }

    @Test
    public void ifNoPrimesInArrayThanHasNextReturnsFalse() {
        assertThat(new PrimeIterator(new int[]{4, 6, 10}).hasNext(), is(false));
    }

    @Test
    public void primeNumbersReturnsCorrectly() {
        PrimeIterator iterator = new PrimeIterator(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18});
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(7));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(11));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(13));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(17));
        assertThat(iterator.hasNext(), is(false));
    }
}