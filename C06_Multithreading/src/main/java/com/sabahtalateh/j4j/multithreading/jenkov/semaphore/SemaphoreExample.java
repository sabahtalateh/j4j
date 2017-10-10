package com.sabahtalateh.j4j.multithreading.jenkov.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * SemaphoreExample.
 */
public class SemaphoreExample {

    /**
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(4);

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            new Thread(new SemaphoreVisitor(semaphore, "V" + i)).start();
            Thread.sleep(400);
        }
    }

    /**
     * SemaphoreVisitor.
     */
    private static class SemaphoreVisitor implements Runnable {
        private final Semaphore semaphore;
        private final String name;

        /**
         * @param semaphore semaphore.
         * @param name      name.
         */
        SemaphoreVisitor(Semaphore semaphore, String name) {
            this.semaphore = semaphore;
            this.name = name;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            try {
                System.out.printf("[%s] acquire lock.%n", name);
                semaphore.acquire();

                System.out.printf("[%s] start working...%n", name);
                Thread.sleep((long) (Math.random() * 2000));

                semaphore.release();
                System.out.printf("[%s] release.%n", name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
