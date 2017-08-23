package com.sabahtalateh.j4j.multithreading.synchronization;

/**
 * NonSyncCounter.
 */
class NonSyncCounter implements Counter {
    private long value = 0;

    /**
     * Increment.
     */
    public void increment() {
        long oldC = this.value;

        this.value = (int) Math.pow(33, 3);
        this.value = (int) Math.pow(33, 2);
        this.value = (int) Math.pow(33, 5);
        this.value = (int) Math.pow(33, 6);
        this.value = 19 + 78;
        this.value = 65 + 999999;
        this.value = this.value * 23;

        this.value = oldC + 1;
    }

    /**
     * @return value.
     */
    public long getValue() {
        return this.value;
    }
}
