package com.sabahtalateh.j4j.collections_advanced.iterator;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * EvenIteratorTest.
 */
public class EvenIteratorTest {

    @Test
    public void ifNoEvensReturnThenNoNext() {
        assertThat(new EvenIterator(new int[]{}).hasNext(), is(false));
        assertThat(new EvenIterator(new int[]{1, 3, 5}).hasNext(), is(false));
    }

    @Test
    public void ifHasEvensThanCanRetrieveIt() {
        EvenIterator iterator = new EvenIterator(new int[]{3, 42, 7, 4, 2, 9});
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(42));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(false));
    }
}
