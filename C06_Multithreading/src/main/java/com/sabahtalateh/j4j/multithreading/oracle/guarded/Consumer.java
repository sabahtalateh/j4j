package com.sabahtalateh.j4j.multithreading.oracle.guarded;

import java.util.Random;

/**
 * Consumer.
 */
public class Consumer implements Runnable {
    private final Drop drop;

    private final Random random = new Random();

    /**
     * @param drop drop.
     */
    Consumer(Drop drop) {
        this.drop = drop;
    }

    /**
     * Run.
     */
    @Override
    public void run() {
        for (String message = drop.take(); !message.equals("DONE"); message = drop.take()) {
            System.out.printf("RECEIVED: %s%n", message);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
