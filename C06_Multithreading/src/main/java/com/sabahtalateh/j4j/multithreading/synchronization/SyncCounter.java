package com.sabahtalateh.j4j.multithreading.synchronization;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * SyncCounter.
 */
@ThreadSafe
public class SyncCounter implements Counter {
    @GuardedBy("this")
    private long value = 0;

    /**
     * Increment.
     */
    public synchronized void increment() {
        long oldC = this.value;

        this.value = (int) Math.pow(33, 3);
        this.value = (int) Math.pow(33, 2);
        this.value = (int) Math.pow(33, 2);
        this.value = (int) Math.pow(33, 6);
        this.value = 19 + 78;
        this.value = 65 + 999999;
        this.value = this.value * 23;

        this.value = oldC + 1;
    }

    /**
     * @return value.
     */
    public synchronized long getValue() {
        return this.value;
    }
}
