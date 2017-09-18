package com.sabahtalateh.j4j.multithreading.wait_notify.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Demo.
 */
public class DemoDeadlock {
    private static ReentrantLock reentrantLock1 = new ReentrantLock();
    private static ReentrantLock reentrantLock2 = new ReentrantLock();

//    private static Lock lock1 = new Lock(true);
//    private static Lock lock2 = new Lock(true);

    /**
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            reentrantLock1.lock();
            reentrantLock1.unlock();

        });

        Thread t2 = new Thread(() -> {
            reentrantLock1.lock();
        });

        t1.start();
        t1.join();
        t2.start();
        t2.join();
    }
}
