package com.sabahtalateh.j4j.collections_advanced.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * ArraySetImprovedTest.
 */
public class ArraySetImprovedTest {
    @Test
    public void name() throws Exception {
        Set<Integer> arraySetImproved = new ArraySetImproved<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1e3; i++) {
            arraySetImproved.add(i);
        }
        long spent = System.currentTimeMillis() - start;
        System.out.printf("Improved %.3f seconds%n", spent / 1000.0);

        Set<Integer> arraySet = new ArraySet<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < 1e3; i++) {
            arraySet.add(i);
        }
        spent = System.currentTimeMillis() - start;
        System.out.printf("Simple %.3f seconds", spent / 1000.0);
    }

    @Test
    public void canIterateThroughAddedElements() throws Exception {
        Set<String> arraySetImproved = new ArraySetImproved<>();
        arraySetImproved.add("1");
        arraySetImproved.add("2");
        arraySetImproved.add("3");
        arraySetImproved.add("1");

        Iterator<String> iterator = arraySetImproved.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("1"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("2"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("3"));
        assertThat(iterator.hasNext(), is(false));
    }
}