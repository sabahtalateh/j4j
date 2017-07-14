package com.sabahtalateh.j4j.collections_lite.convert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

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


}