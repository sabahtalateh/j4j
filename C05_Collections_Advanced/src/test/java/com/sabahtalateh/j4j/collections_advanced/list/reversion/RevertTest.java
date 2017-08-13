package com.sabahtalateh.j4j.collections_advanced.list.reversion;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * RevertTest.
 */
public class RevertTest {

    @Test
    public void oneElementListIsRevertsToItself() throws Exception {
        Node<String> node1 = new Node<>("1");
        assertThat(new Revert().revert(node1), is(node1));
    }

    @Test
    public void whenRevertListThenIsReverts() throws Exception {
        Node<String> node1 = new Node<>("1");
        Node<String> node2 = new Node<>("2");
        Node<String> node3 = new Node<>("3");
        Node<String> node4 = new Node<>("4");

        node1.appendNext(node2);
        node2.appendNext(node3);
        node3.appendNext(node4);

        Node<String> expected = new Node<>("4");
        Node<String> expectedNext1 = new Node<>("3");
        Node<String> expectedNext2 = new Node<>("2");
        Node<String> expectedNext3 = new Node<>("1");

        expected.appendNext(expectedNext1);
        expectedNext1.appendNext(expectedNext2);
        expectedNext2.appendNext(expectedNext3);

        Node<String> reverted = new Revert().revert(node1);

        assertThat(reverted, is(expected));
        assertThat(reverted.getNext(), is(expectedNext1));
        assertThat(reverted.getNext().getNext(), is(expectedNext2));
        assertThat(reverted.getNext().getNext().getNext(), is(expectedNext3));
    }
}