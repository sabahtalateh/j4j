package com.sabahtalateh.j4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests.
 */
public class CounterTest {
    @Test
    public void sumEven() throws Exception {
        Counter counter = new Counter();

        assertThat(counter.sumEven(0, 0), is(0));
        assertThat(counter.sumEven(0, 1), is(0));
        assertThat(counter.sumEven(100, 100), is(100));
        assertThat(counter.sumEven(0, 10), is(30));
        assertThat(counter.sumEven(10, 12), is(22));
    }
}