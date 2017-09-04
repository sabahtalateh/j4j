package com.sabahtalateh.j4j.multithreading.list;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * ArrayListTest.
 */
public class ArrayListTest {
    @Test
    public void testAdd() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("First");
        list.add("Second");

        assertThat(list.get(0), is("First"));
        assertThat(list.get(1), is("Second"));
        assertThat(list.size(), is(2));
    }
}