package com.sabahtalateh.j4j.collections_advanced.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * ArrayContainerTest.
 */
public class ArrayContainerTest {
    @Test
    public void whenAddElementToContainerThenCanRetrieveIt() {
        ArrayContainer<Integer> arrayContainer = new ArrayContainer<>(1);

        arrayContainer.add(1);
        assertThat(arrayContainer.get(0), is(1));

        arrayContainer.add(2);
        assertThat(arrayContainer.get(1), is(2));

        arrayContainer.add(3);
        assertThat(arrayContainer.get(2), is(3));

        arrayContainer.add(3);
        assertThat(arrayContainer.get(2), is(3));
    }

    @Test
    public void testIteratorReturnsCorrectValues() throws Exception {
        ArrayContainer<Integer> arrayContainer = new ArrayContainer<>();
        arrayContainer.add(1);
        arrayContainer.add(2);
        arrayContainer.add(3);

        assertThat(arrayContainer.hasNext(), is(true));
        assertThat(arrayContainer.next(), is(1));
        assertThat(arrayContainer.hasNext(), is(true));
        assertThat(arrayContainer.next(), is(2));
        assertThat(arrayContainer.hasNext(), is(true));
        assertThat(arrayContainer.next(), is(3));
        assertThat(arrayContainer.hasNext(), is(false));
    }
}