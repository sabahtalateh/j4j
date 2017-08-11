package com.sabahtalateh.j4j.collections_advanced.list;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * LinkedContainerTest.
 */
public class LinkedContainerTest {
    @Test
    public void whenSomethingPutsInListThanItCanBeRetrieved() throws Exception {
        LinkedContainer<Integer> linkedList = new LinkedContainer<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        assertThat(linkedList.get(0), is(1));
        assertThat(linkedList.get(1), is(2));
        assertThat(linkedList.get(2), is(3));
    }

    @Test
    public void whenSomethingPutsInListThenItCanBeRetrievedViaIterator() throws Exception {
        LinkedContainer<Integer> linkedList = new LinkedContainer<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        assertThat(linkedList.hasNext(), is(true));
        assertThat(linkedList.next(), is(1));
        assertThat(linkedList.hasNext(), is(true));
        assertThat(linkedList.next(), is(2));
        assertThat(linkedList.hasNext(), is(true));
        assertThat(linkedList.next(), is(3));
        assertThat(linkedList.hasNext(), is(false));
    }

    @Test
    public void iteratorOnEmptyListWilReturnFalse() throws Exception {
        LinkedContainer<Integer> linkedList = new LinkedContainer<>();
        assertThat(linkedList.hasNext(), is(false));
    }
}