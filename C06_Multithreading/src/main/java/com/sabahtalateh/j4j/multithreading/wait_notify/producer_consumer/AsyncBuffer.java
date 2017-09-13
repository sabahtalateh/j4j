package com.sabahtalateh.j4j.multithreading.wait_notify.producer_consumer;

import net.jcip.annotations.ThreadSafe;

/**
 * @param <E> Elements type.
 */
@ThreadSafe
public class AsyncBuffer<E> {

    private E msg;
    private boolean empty = true;
    private final Object lock = new Object();

    /**
     * @param msg message.
     * @throws InterruptedException exception.
     */
    public void put(E msg) throws InterruptedException {
        synchronized (lock) {
            while (!empty) {
                lock.wait();
            }
            this.msg = msg;
            this.empty = false;
            lock.notifyAll();
        }
    }

    /**
     * @return message.
     * @throws InterruptedException exception.
     */
    public E take() throws InterruptedException {
        synchronized (lock) {
            while (empty) {
                lock.wait();
            }
            empty = true;
            lock.notifyAll();
            return msg;
        }
    }
}
