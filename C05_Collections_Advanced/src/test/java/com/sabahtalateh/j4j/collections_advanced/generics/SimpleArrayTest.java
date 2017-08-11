package com.sabahtalateh.j4j.collections_advanced.generics;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * SimpleArrayTest.
 */
public class SimpleArrayTest {
    @Test
    public void whenAddElementThenCanRetrieveIt() {
        SimpleArray<? super Number> numbers = new SimpleArray<>();
        numbers.add(1);
        numbers.add(1.2);

        assertThat(numbers.get(0), is(1));
        assertThat(numbers.get(1), is(1.2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveElementThenCanNotRetrieveIt() {
        SimpleArray<? super Number> numbers = new SimpleArray<>();
        numbers.add(1);

        numbers.delete(1);
        numbers.get(0);
    }

    @Test
    public void whenUpdateElementThenItIsUpdated() throws Exception {
        SimpleArray<? super Number> numbers = new SimpleArray<>();
        numbers.add(1);
        numbers.add(1.2);

        numbers.update(1, 5);
        assertThat(numbers.get(1), is(5));
    }
}