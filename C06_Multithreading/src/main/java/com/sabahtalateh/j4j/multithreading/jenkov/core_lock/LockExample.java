package com.sabahtalateh.j4j.multithreading.jenkov.core_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LockExample.
 */
public class LockExample {
    /**
     * @param args args.
     */
    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                Thread.sleep(2000);
                lock.unlock();
            } catch (InterruptedException e) {
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(200);
                t1.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

    }
}
