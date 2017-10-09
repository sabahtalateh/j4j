package com.sabahtalateh.j4j.multithreading.jenkov.nested_lockout;

/**
 * Demo.
 */
public class Demo {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        Lock lock = new Lock();

        new Thread(() -> {
            try {
                lock.lock();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("Unlock");
            lock.unlock();
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("Unlock");
            lock.unlock();
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("Unlock");
            lock.unlock();
        }).start();

    }
}
