package com.sabahtalateh.j4j.multithreading.synchronization;

/**
 * Counter.
 */
public interface Counter {
    /**
     * Increment.
     */
    void increment();

    /**
     * @return value.
     */
    long getValue();
}
