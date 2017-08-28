package com.sabahtalateh.j4j.multithreading.stdlib_classes;

import java.util.concurrent.CountDownLatch;

/**
 * CountdownLatchExample.
 * <p>
 * Cars racing.
 * First of all we should wait when all the racers will be on positions.
 * Only then we can start the race.
 */
public class CountdownLatchExample {
    private static final int CARS = 5;
    private static final CountDownLatch START = new CountDownLatch(CARS + 3); // 3 for count down before start.
    private static final int TRACK_LENGTH = 500000;

    /**
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {

        Thread[] t = new Thread[CARS];

        for (int i = 0; i < CARS; i++) {
            t[i] = new Thread(new Car(i, (int) (Math.random() * 1000 + 50)));
        }

        for (int i = 0; i < CARS; i++) {
            t[i].start();
            Thread.sleep((int) (Math.random() * 1000));
        }

        // Check if all cars are in front of the starting line.
        while (START.getCount() > 3) {
            System.out.printf("Waiting for drivers, [%s] left.%n", (CARS - 3 - START.getCount()));
            Thread.sleep(500);
        }

        Thread.sleep(1000);
        System.out.println("Ready.");
        START.countDown();

        Thread.sleep(1000);
        System.out.println("Steady.");
        START.countDown();

        Thread.sleep(1000);
        System.out.println("Go.");
        START.countDown();

    }

    /**
     * Car.
     */
    private static class Car implements Runnable {
        private int carNumber;
        private int carSpeed;

        /**
         * @param carNumber number.
         * @param carSpeed  speed.
         */
        Car(int carNumber, int carSpeed) {
            this.carNumber = carNumber;
            this.carSpeed = carSpeed;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            try {
                System.out.printf("[Car %s] arrived to start line.%n", carNumber);

                // Car is on the start line, decrease count down.
                START.countDown();

                // Block this thread until counter is not equals to 0.
                START.await();

                // Drive the track.
                Thread.sleep(TRACK_LENGTH / carSpeed);
                System.out.printf("[Car %s] finished.%n", carNumber);
            } catch (InterruptedException e) {
            }
        }
    }

}
