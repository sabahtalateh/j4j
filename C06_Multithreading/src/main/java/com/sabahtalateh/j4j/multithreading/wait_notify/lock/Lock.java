package com.sabahtalateh.j4j.multithreading.wait_notify.lock;

import java.util.UUID;

/**
 * Lock.
 */
public class Lock {

    private volatile Thread locker;

    private final boolean diagnosticMode;

    private final String lockId = UUID.randomUUID().toString().substring(0, 4);

    private volatile int lockersCount = 0;

    /**
     * Lock.
     */
    public Lock() {
        this(false);
    }

    /**
     * @param diagnosticMode diagnostic mode.
     */
    public Lock(boolean diagnosticMode) {
        this.diagnosticMode = diagnosticMode;
    }

    /**
     * Lock.
     */
    public synchronized void lock() {
        if (lockersCount == 0) {
            locker = Thread.currentThread();
            lockersCount++;
            if (diagnosticMode) {
                locker = Thread.currentThread();
                System.out.printf("[%s] acquired (%d locks) by [%s].%n", lockId, lockersCount, locker.getName());
            }
        } else if (lockersCount > 0 && locker == Thread.currentThread()) {
            lockersCount++;
        } else {
            try {
                this.wait();
                this.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Unlock.
     */
    public synchronized void unlock() {
        if (lockersCount == 0 || locker != Thread.currentThread()) {
            throw new IllegalMonitorStateException("No lockers.");
        }

        lockersCount--;
        if (diagnosticMode) {
            System.out.printf("[%s] released (%d remains) by [%s]", lockId, lockersCount, Thread.currentThread());
        }
        if (lockersCount == 0) {
            this.notify();
        }
    }
}
