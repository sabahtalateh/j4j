package com.sabahtalateh.j4j.multithreading.stdlib_classes;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrierExample.
 * <p>
 * Cars travels through the river with ferry.
 */
public class CyclicBarrierExample {

    // Init 3 threads barrier with action that will be performed when all 3 threads will be ready.
    // After the action all the threads will be released.
    private static final CyclicBarrier BARRIER = new CyclicBarrier(2, new FerryBoat());

    /**
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(1000);
        }
    }

    /**
     * FerryBoat.
     */
    private static class FerryBoat implements Runnable {
        /**
         * Run.
         */
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println("Ferry moved the cars.");
                Thread.sleep(1000);
                System.out.println("Ferry moved back.");
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Car.
     */
    private static class Car implements Runnable {
        private int carNumber;

        /**
         * @param carNumber number.
         */
        Car(int carNumber) {
            this.carNumber = carNumber;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            try {
                System.out.printf("[Car %s] drove up the ferry.%n", carNumber);
                // Await indicates that this thread has reached the barrier.
                // This thread will be blocked until the other threads reached the barrier.
                BARRIER.await();
                System.out.printf("[Car %s] continued movement.%n", carNumber);
            } catch (BrokenBarrierException | InterruptedException e) {
            }
        }
    }
}
