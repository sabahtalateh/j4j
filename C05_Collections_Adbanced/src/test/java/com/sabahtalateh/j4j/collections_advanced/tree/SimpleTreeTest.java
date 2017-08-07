package com.sabahtalateh.j4j.collections_advanced.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * SimpleTreeTest.
 */
public class SimpleTreeTest {
    @Test
    public void canNotAppendChildToItself() throws Exception {
        SimpleTree<String> tree = new SimpleTree<>("10");
        assertThat(tree.add("10", "10"), is(false));
    }

    @Test
    public void canNotAppendToNonExistingNode() throws Exception {
        SimpleTree<String> tree = new SimpleTree<>("10");
        assertThat(tree.add("100", "101"), is(false));
    }

    @Test
    public void whenAddSomeNodesThanCanIterateThroughThem() throws Exception {
        SimpleTree<Integer> tree = new SimpleTree<>(5);

        tree.add(5, 3);
        tree.add(5, 7);
        tree.add(3, 1);
        tree.add(3, 2);
        tree.add(3, 4);
        tree.add(7, 6);
        tree.add(7, 8);
        tree.add(8, 9);
        tree.add(9, 10);

        Iterator<Integer> iterator = tree.iterator();
        List<Integer> list = new ArrayList<>();
        iterator.forEachRemaining(list::add);
        list.sort(Integer::compareTo);

        ArrayList<Integer> expected = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
            add(8);
            add(9);
            add(10);
        }};
        assertThat(list, is(expected));
    }
}