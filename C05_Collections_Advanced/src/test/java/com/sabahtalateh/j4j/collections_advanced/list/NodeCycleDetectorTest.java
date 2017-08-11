package com.sabahtalateh.j4j.collections_advanced.list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * NodeCycleDetectorTest.
 */
public class NodeCycleDetectorTest {
    @Test
    public void ifTreeHasCyclesThanHasCyclesReturnsTrue() throws Exception {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);

        first.setNext(second);
        second.setNext(third);
        third.setNext(fourth);
        fourth.setNext(first);

        NodeCycleDetector nodeCycleDetector = new NodeCycleDetector();
        assertThat(nodeCycleDetector.hasCycles(first), is(true));
        assertThat(nodeCycleDetector.hasCycles(second), is(true));
        assertThat(nodeCycleDetector.hasCycles(third), is(true));
        assertThat(nodeCycleDetector.hasCycles(fourth), is(true));
    }

    @Test
    public void ifTreeDoNotHasCyclesThanHasCyclesReturnsTrue() throws Exception {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);

        first.setNext(second);
        second.setNext(third);
        third.setNext(fourth);

        NodeCycleDetector nodeCycleDetector = new NodeCycleDetector();
        assertThat(nodeCycleDetector.hasCycles(first), is(false));
        assertThat(nodeCycleDetector.hasCycles(second), is(false));
        assertThat(nodeCycleDetector.hasCycles(third), is(false));
        assertThat(nodeCycleDetector.hasCycles(fourth), is(false));
    }
}