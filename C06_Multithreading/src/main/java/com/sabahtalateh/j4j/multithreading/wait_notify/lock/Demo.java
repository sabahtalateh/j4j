package com.sabahtalateh.j4j.multithreading.wait_notify.lock;

/**
 * Demo.
 */
public class Demo {

    private static Lock l1 = new Lock(true);
    private static Lock l2 = new Lock(true);

    /**
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                l1.lock();
                l2.lock();
                Thread.sleep(20000);

                l2.unlock();
                l1.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2000);
                l1.lock();
                l2.lock();

                Thread.sleep(20000);


                l2.unlock();
                l1.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
