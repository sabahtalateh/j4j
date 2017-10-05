package com.sabahtalateh.j4j.multithreading.jenkov.nested_lockout;

/**
 * Lock.
 */
public class Lock {
    private final Object monitor = new Object();
    private boolean locked = false;

    /**
     * @throws InterruptedException exception.
     */
    public void lock() throws InterruptedException {
        synchronized (this) {
            System.out.printf("[%s][Lock] in sync this.%n", Thread.currentThread().getName());
            while (locked) {
                synchronized (monitor) {
                    System.out.printf("[%s][Wait].%n", Thread.currentThread().getName());
                    monitor.wait();
                }
            }
            System.out.printf("[%s][Locked].%n", Thread.currentThread().getName());
            locked = true;
        }
    }

    /**
     * Unlock.
     */
    public void unlock() {
        synchronized (this) {
            System.out.printf("[%s][Unlock] in sync this.%n", Thread.currentThread().getName());
            locked = false;
            synchronized (monitor) {
                monitor.notify();
            }
        }
    }
}
