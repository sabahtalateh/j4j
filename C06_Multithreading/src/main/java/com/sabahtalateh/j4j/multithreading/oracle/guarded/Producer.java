package com.sabahtalateh.j4j.multithreading.oracle.guarded;

import java.util.Random;

/**
 * Producer.
 */
public class Producer implements Runnable {

    private final Drop drop;

    private final String[] messages = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
    };

    private final Random random = new Random();

    /**
     * @param drop drop.
     */
    Producer(Drop drop) {
        this.drop = drop;
    }

    /**
     * Run.
     */
    @Override
    public void run() {
        for (String message : this.messages) {
            drop.put(message);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        drop.put("DONE");
    }
}
