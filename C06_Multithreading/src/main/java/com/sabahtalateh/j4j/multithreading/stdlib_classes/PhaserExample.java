package com.sabahtalateh.j4j.multithreading.stdlib_classes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

/**
 * PhaserExample.
 */
public class PhaserExample {
    // 1 is for main thread.
    // If not set 1 then we can not use Phaser in main thread.
    public static final Phaser PHASER = new Phaser(1);

    /**
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        List<Passenger> passengers = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            if ((int) (Math.random() * 2) > 0) {
                // This passenger quits next stop.
                passengers.add(new Passenger(i, i + 1));
            }
            if ((int) (Math.random() * 2) > 0) {
                // This passenger quits on fifth stop.
                passengers.add(new Passenger(i, 5));
            }
        }

        Thread.sleep(10000);

        // 0 and 6 is base.
        for (int i = 0; i < 7; i++) {
            Thread.sleep((int) (Math.random() * 10000));
            switch (i) {
                case 0:
                    System.out.println("Bus is moved out the base.");
                    PHASER.arrive();

                    break;
                case 6:
                    System.out.println("Bus ended its route and drove back to the base.");
                    PHASER.arriveAndDeregister();

                    break;
                default:
                    int currentStop = PHASER.getPhase();
                    System.out.printf("[Stop %d] arrived.%n", currentStop);

                    for (Passenger p : passengers) {
                        if (p.departure == currentStop) {
                            PHASER.register();
                            p.start();
                        }
                    }
                    PHASER.arriveAndAwaitAdvance();
            }
        }
    }

    /**
     * Passenger.
     */
    private static class Passenger extends Thread {
        private int departure;
        private int destination;

        /**
         * @param departure   departure.
         * @param destination destination.
         */
        Passenger(int departure, int destination) {
            this.departure = departure;
            this.destination = destination;
            System.out.printf("[%s] is waiting on the [%d] stop.%n", this, departure);
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            try {
                System.out.printf("[%s] got the bus.%n", this);
                while (PHASER.getPhase() < destination) {
                    PHASER.arriveAndAwaitAdvance();
                }
                Thread.sleep((int) (Math.random() * 10000));
                System.out.printf("[%s] left the bus.%n", this);
                PHASER.arriveAndDeregister();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * @return string.
         */
        @Override
        public String toString() {
            return "Passenger{departure=" + departure + ", destination=" + destination + '}';
        }
    }
}
