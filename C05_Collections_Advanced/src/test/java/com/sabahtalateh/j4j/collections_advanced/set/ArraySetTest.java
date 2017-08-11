package com.sabahtalateh.j4j.collections_advanced.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * ArraySetTest.
 */
public class ArraySetTest {
    @Test
    public void canIterateThroughAddedElements() throws Exception {
        Set<String> arraySet = new ArraySet<>();
        arraySet.add("Hello");
        arraySet.add("World");
        arraySet.add("Good bue");
        arraySet.add("World");

        Iterator<String> iterator = arraySet.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Hello"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("World"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Good bue"));
        assertThat(iterator.hasNext(), is(false));
    }
}