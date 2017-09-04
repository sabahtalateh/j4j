package com.sabahtalateh.j4j.multithreading.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * LinkedListTest.
 */
public class LinkedListTest {
    @Test
    public void whenElementAddedItCanBeRetrieved() throws Exception {
        List<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");

        assertThat(list.get(0), is("1"));
        assertThat(list.get(1), is("2"));
    }

    @Test
    public void whenElementPastedByIndexThenItCanBeRetrieved() throws Exception {
        List<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");
        assertThat(list.get(1), is("2"));

        list.add(1, "100");
        assertThat(list.get(1), is("100"));
        assertThat(list.get(2), is("2"));
    }

    @Test
    public void whenElementsAddedThenContainsReturnsTrue() throws Exception {
        List<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");

        assertThat(list.contains("1"), is(true));
        assertThat(list.contains("2"), is(true));
        assertThat(list.contains("100"), is(false));

        list.add(1, "100");
        assertThat(list.contains("100"), is(true));
    }

    @Test
    public void testIsEmpty() throws Exception {
        List<String> list = new LinkedList<>();
        assertThat(list.isEmpty(), is(true));
        list.add("1");
        assertThat(list.isEmpty(), is(false));
    }

    @Test
    public void whenRemoveThenElementsCanNotBeRetrieved() throws Exception {
        List<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        assertThat(list.size(), is(3));
        assertThat(list.remove(100), is(false));
        assertThat(list.remove(1), is(true));
        assertThat(list.get(1), is("3"));
        assertThat(list.get(0), is("1"));
        assertThat(list.size(), is(2));
    }
}
