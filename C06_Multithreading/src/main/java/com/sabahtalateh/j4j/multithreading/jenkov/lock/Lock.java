package com.sabahtalateh.j4j.multithreading.jenkov.lock;

/**
 * Lock.
 */
public class Lock {
    private boolean locked = false;
    private Thread lockOwner = null;

    /**
     * @throws InterruptedException exception.
     */
    public synchronized void lock() throws InterruptedException {
        while (locked) {
            this.wait();
        }
        this.locked = true;
        this.lockOwner = Thread.currentThread();
    }

    /**
     * Unlock.
     */
    public synchronized void unlock() {
        if (lockOwner != Thread.currentThread()) {
            throw new IllegalMonitorStateException("Only lock owner thread can unlock.");
        }
        this.locked = false;
        this.lockOwner = null;
        this.notify();
    }
}
