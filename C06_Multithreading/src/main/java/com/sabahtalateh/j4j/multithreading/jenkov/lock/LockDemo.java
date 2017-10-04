package com.sabahtalateh.j4j.multithreading.jenkov.lock;

/**
 * LockDemo.
 */
public class LockDemo {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        Lock lock = new Lock();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.printf("[%s] working.%n", Thread.currentThread().getName());
                Thread.sleep(1500);
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                System.out.printf("[%s] working.%n", Thread.currentThread().getName());
                Thread.sleep(2000);
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
