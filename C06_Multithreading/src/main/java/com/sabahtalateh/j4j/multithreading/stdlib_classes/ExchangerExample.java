package com.sabahtalateh.j4j.multithreading.stdlib_classes;

import java.util.concurrent.Exchanger;

/**
 * ExchangerExample.
 */
public class ExchangerExample {
    private static final Exchanger<String> EXCHANGER = new Exchanger<>();

    /**
     * @param args args.
     */
    public static void main(String[] args) {
        String[] p1 = new String[]{"{Parcel [A->D]}", "{Parcel [A->C]}"};
        String[] p2 = new String[]{"{Parcel [B->C]}", "{Parcel [B->D]}"};

        new Thread(new Truck(1, "A", "D", p1)).start();
        new Thread(new Truck(2, "B", "C", p2)).start();
    }

    /**
     * Truck.
     */
    private static class Truck implements Runnable {
        private int truckNumber;
        private String departure;
        private String destination;
        private String[] parcels;

        /**
         * @param truckNumber number.
         * @param departure   departure.
         * @param destination destination.
         * @param parcels     parcels.
         */
        Truck(int truckNumber, String departure, String destination, String[] parcels) {
            this.truckNumber = truckNumber;
            this.departure = departure;
            this.destination = destination;
            this.parcels = parcels;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            try {
                System.out.printf("[Truck %d] moved from [%s] to [%s].%n", truckNumber, departure, destination);
                Thread.sleep((int) (Math.random() * 3000));
                System.out.printf("[Truck %d] was loaded with [%s parcel] and [%s parcel].%n", truckNumber, parcels[0], parcels[1]);
                Thread.sleep(1000 + (int) (Math.random() * 5000));
                System.out.printf("[Truck %s] arrived to [E].%n", truckNumber);

                // When exchange calls, current thread blocks and wait for another thread calls exchange.
                // And then exchanges with other thread.
                parcels[1] = EXCHANGER.exchange(parcels[1]);

                System.out.printf("[Truck %d] was loaded with parcel for [%s].%n", truckNumber, destination);
                Thread.sleep(1000 + (int) (Math.random() * 5000));
                System.out.printf("[Truck %d] arrived to [%s] and shipped [%s] and [%s].%n", truckNumber, destination, parcels[0], parcels[1]);
            } catch (InterruptedException e) {
            }
        }
    }
}
