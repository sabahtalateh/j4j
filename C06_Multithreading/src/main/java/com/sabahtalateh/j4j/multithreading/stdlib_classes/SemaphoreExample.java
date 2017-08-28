package com.sabahtalateh.j4j.multithreading.stdlib_classes;

import java.util.concurrent.Semaphore;

/**
 * SemaphoreExample.
 * <p>
 * Car parking.
 * Parking has limited places.
 * Cars arrive to the parking and wait while it will be free parking place.
 */
public class SemaphoreExample {
    private static final int PLACES = 4;
    private static final boolean[] PARKING_PLACES = new boolean[PLACES];
    private static final Semaphore SEMAPHORE = new Semaphore(PLACES);

    /**
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(400);
        }
    }

    /**
     * Car.
     */
    public static class Car implements Runnable {

        private int carNumber;

        /**
         * @param carNumber car number.
         */
        Car(int carNumber) {
            this.carNumber = carNumber;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            System.out.printf("[Car %s] arrive to parking.%n", carNumber);
            try {
                // Acquires access to code after this call until semaphore semaphore will not permit it.
                SEMAPHORE.acquire();

                int parkingNumber = -1;

                // Looking for free parking place.
                synchronized (PARKING_PLACES) {
                    for (int i = 0; i < PLACES; i++) {
                        if (!PARKING_PLACES[i]) {           // If there is free place.
                            PARKING_PLACES[i] = true;       // Take it.
                            parkingNumber = i;              // Semaphore guarantees parking place accessible.
                            System.out.printf("[Car %s] parked to [%s place].%n", carNumber, parkingNumber);
                            break;
                        }
                    }
                }

                // Do the stuff.
                Thread.sleep(5000);

                synchronized (PARKING_PLACES) {
                    PARKING_PLACES[parkingNumber] = false;
                }

                SEMAPHORE.release();
                System.out.printf("[Car %s] left [%s place].%n", carNumber, parkingNumber);

            } catch (InterruptedException e) {
            }
        }
    }
}
