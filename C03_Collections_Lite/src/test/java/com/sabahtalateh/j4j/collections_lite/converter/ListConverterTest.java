package com.sabahtalateh.j4j.collections_lite.converter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * ListConverterTest.
 */
public class ListConverterTest {
    @Test
    public void toList() throws Exception {
        ListConverter listConverter = new ListConverter();
        int[][] input = new int[][]{{1, 2, 3}, {4}, {5, 6}, {7}};
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);

        assertThat(listConverter.toList(input), is(expected));
    }

    @Test
    public void toArray() throws Exception {
        ListConverter listConverter = new ListConverter();

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);

        int[][] expected = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        assertThat(listConverter.toArray(list, 3), is(expected));
    }
}