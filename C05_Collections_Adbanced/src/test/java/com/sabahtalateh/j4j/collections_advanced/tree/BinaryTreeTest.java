package com.sabahtalateh.j4j.collections_advanced.tree;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * BinaryTreeTest.
 */
public class BinaryTreeTest {
    @Test
    public void binaryTreeTest() throws Exception {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();

        binaryTree.add(10);
        binaryTree.add(4);
        binaryTree.add(12);
        binaryTree.add(6);
        binaryTree.add(12);

        assertThat(binaryTree.contains(10), is(true));
        assertThat(binaryTree.contains(4), is(true));
        assertThat(binaryTree.contains(12), is(true));
        assertThat(binaryTree.contains(6), is(true));
        assertThat(binaryTree.contains(100), is(false));
    }
}