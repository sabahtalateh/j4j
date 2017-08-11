package com.sabahtalateh.j4j.collections_advanced.list;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * QueueTest.
 */
public class QueueTest {
    @Test
    public void queueTest() throws Exception {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertThat(queue.dequeue(), is(1));
        assertThat(queue.dequeue(), is(2));
        assertThat(queue.dequeue(), is(3));
    }
}