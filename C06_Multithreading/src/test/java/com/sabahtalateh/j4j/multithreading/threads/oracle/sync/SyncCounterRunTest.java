package com.sabahtalateh.j4j.multithreading.threads.oracle.sync;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * NonSyncCounterRunTest.
 */
public class SyncCounterRunTest {

    @Test
    public void testNonSyncCounter() throws Exception {
        int res1 = SyncCounterRun.increment100Sync();
        int res2 = SyncCounterRun.increment100Sync();
        assertThat(res1, is(res2));
        assertThat(res1, is(100));
        assertThat(res2, is(100));
    }
}