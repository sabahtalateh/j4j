package com.sabahtalateh.j4j.multithreading.list;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * ArrayListTest.
 */
public class ArrayListTest {
    @Test
    public void whenElementAddedThenItCanBeRetrieved() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("First");
        list.add("Second");

        assertThat(list.get(0), is("First"));
        assertThat(list.get(1), is("Second"));
        assertThat(list.size(), is(2));
    }

    @Test
    public void whenElementDeletedItCanNotBeRetrieved() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("First");
        list.add("Second");
        list.add("Third");

        assertThat(list.get(0), is("First"));
        assertThat(list.remove(0), is(true));
        assertThat(list.remove(1000), is(false));
        assertThat(list.get(0), is("Second"));
        assertThat(list.contains("First"), is(false));
    }

    @Test
    public void whenAddElementsToSameIndexThenItBothCanBeRetrieved() throws Exception {
        List<String> list = new ArrayList<>();
        list.add(1, "KK");
        list.add(1, "KK");
        assertThat(list.get(1), is("KK"));
        assertThat(list.get(2), is("KK"));
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