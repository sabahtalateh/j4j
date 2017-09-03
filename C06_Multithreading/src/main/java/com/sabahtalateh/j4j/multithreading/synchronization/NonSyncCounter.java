package com.sabahtalateh.j4j.multithreading.synchronization;

/**
 * NonSyncCounter.
 */
public class NonSyncCounter implements Counter {
    private long value = 0;

    /**
     * Increment.
     */
    public void increment() {
        this.value += 1;
    }

    /**
     * @return value.
     */
    public long getValue() {
        return this.value;
    }
}
