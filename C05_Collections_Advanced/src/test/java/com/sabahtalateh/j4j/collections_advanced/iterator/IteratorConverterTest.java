package com.sabahtalateh.j4j.collections_advanced.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * IteratorConverterTest.
 */
public class IteratorConverterTest {

    @Test
    public void whenIteratorDoesNptContainInnerIteratorsThenNoNext() throws Exception {
        IteratorConverter iteratorConverter = new IteratorConverter();

        ArrayList<Iterator<Integer>> iterators = new ArrayList<>();
        Iterator<Integer> convertedIterator = iteratorConverter.convert(iterators.iterator());

        assertThat(convertedIterator.hasNext(), is(false));
    }

    @Test
    public void onEmptyIteratorHasNextWillReturnFalse() {
        ArrayList<Integer> integers1 = new ArrayList<>();

        ArrayList<Integer> integers2 = new ArrayList<>();

        ArrayList<Iterator<Integer>> iterators = new ArrayList<>();
        iterators.add(integers1.iterator());
        iterators.add(integers2.iterator());

        IteratorConverter iteratorConverter = new IteratorConverter();
        Iterator<Integer> convertedIterator = iteratorConverter.convert(iterators.iterator());

        assertThat(convertedIterator.hasNext(), is(false));
    }

    @Test
    public void ifSomeOfInnerIteratorsAreEmptyEverythingWorksAsExpected() {
        ArrayList<Integer> integers1 = new ArrayList<>();

        ArrayList<Integer> integers2 = new ArrayList<Integer>() {{
            add(1);
            add(2);
        }};

        ArrayList<Iterator<Integer>> iterators = new ArrayList<>();
        iterators.add(integers1.iterator());
        iterators.add(integers2.iterator());

        IteratorConverter iteratorConverter = new IteratorConverter();
        Iterator<Integer> convertedIterator = iteratorConverter.convert(iterators.iterator());

        assertThat(convertedIterator.hasNext(), is(true));
        assertThat(convertedIterator.next(), is(1));
        assertThat(convertedIterator.hasNext(), is(true));
        assertThat(convertedIterator.next(), is(2));
        assertThat(convertedIterator.hasNext(), is(false));
    }

    @Test
    public void iteratorValueCanBeCorrectlyRetrieved() {
        ArrayList<Integer> integers0 = new ArrayList<Integer>();

        ArrayList<Integer> integers1 = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }};

        ArrayList<Integer> integers2 = new ArrayList<>();
        ArrayList<Integer> integers3 = new ArrayList<>();

        ArrayList<Integer> integers4 = new ArrayList<Integer>() {{
            add(4);
            add(5);
        }};

        ArrayList<Integer> integers5 = new ArrayList<>();

        ArrayList<Iterator<Integer>> iterators = new ArrayList<>();
        iterators.add(integers0.iterator());
        iterators.add(integers0.iterator());
        iterators.add(integers1.iterator());
        iterators.add(integers2.iterator());
        iterators.add(integers3.iterator());
        iterators.add(integers4.iterator());
        iterators.add(integers5.iterator());

        IteratorConverter iteratorConverter = new IteratorConverter();
        Iterator<Integer> convertedIterator = iteratorConverter.convert(iterators.iterator());

        assertThat(convertedIterator.hasNext(), is(true));
        assertThat(convertedIterator.next(), is(1));
        assertThat(convertedIterator.hasNext(), is(true));
        assertThat(convertedIterator.next(), is(2));
        assertThat(convertedIterator.hasNext(), is(true));
        assertThat(convertedIterator.next(), is(3));
        assertThat(convertedIterator.hasNext(), is(true));
        assertThat(convertedIterator.next(), is(4));
        assertThat(convertedIterator.hasNext(), is(true));
        assertThat(convertedIterator.next(), is(5));
        assertThat(convertedIterator.hasNext(), is(false));
    }
}