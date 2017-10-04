package com.sabahtalateh.j4j.multithreading.jenkov.lock;

/**
 * QueueObject.
 */
public class QueueObject {
    private boolean isNotified = false;

    /**
     * Wait.
     *
     * @throws InterruptedException exception.
     */
    public synchronized void doWait() throws InterruptedException {
        while (!this.isNotified) {
            this.wait();
        }
        this.isNotified = false;
    }

    /**
     * Notify.
     */
    public synchronized void doNotify() {
        this.isNotified = true;
        this.notify();
    }

    /**
     * @param o object.
     * @return equals.
     */
    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    /**
     * @return hash.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
