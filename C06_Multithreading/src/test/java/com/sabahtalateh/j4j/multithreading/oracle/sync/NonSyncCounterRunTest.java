package com.sabahtalateh.j4j.multithreading.oracle.sync;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

/**
 * NonSyncCounterRunTest.
 */
public class NonSyncCounterRunTest {

    @Test
    public void testNonSyncCounter() throws Exception {
        int res1 = CounterRun.increment100();
        int res2 = CounterRun.increment100();
        assertThat(res1, not(res2));
    }
}