package com.sabahtalateh.j4j.collections_advanced.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * LinkedSetTest.
 */
public class LinkedSetTest {
    @Test
    public void whenItemsAddedToSetTheyCanBeRetrieved() throws Exception {
        Set<String> linkedSet = new LinkedSet<>();
        linkedSet.add("Hello");
        linkedSet.add("World");
        linkedSet.add("Hello");
        linkedSet.add("Java");

        Iterator<String> iterator = linkedSet.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Hello"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("World"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Java"));
        assertThat(iterator.hasNext(), is(false));
    }
}