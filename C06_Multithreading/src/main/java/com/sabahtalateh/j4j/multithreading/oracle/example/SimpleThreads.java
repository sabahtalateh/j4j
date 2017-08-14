package com.sabahtalateh.j4j.multithreading.oracle.example;

import java.util.Arrays;

/**
 * SimpleThreads.
 */
public class SimpleThreads {

    /**
     * @param args args.
     * @throws InterruptedException exception
     */
    public static void main(String[] args) throws InterruptedException {
        long patience = 1000;

        System.out.println(Arrays.toString(args));

        if (args.length > 0) {
            try {
                patience = Long.parseLong(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Arg should be integer");
                System.exit(1);
            }
        }

        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("Waiting for MessageLoop thread to finish.");

        while (t.isAlive()) {
            threadMessage("Still waiting..");
            // Wait maximum 1 second for MessageLoop thread to finish.
            t.join(1000);

            if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                t.join();
            }
        }

        threadMessage("Finally.");
    }


    /**
     * @param message message.
     */
    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.printf("%s: %s%n", threadName, message);
    }

    /**
     * MessageLoop.
     */
    private static class MessageLoop implements Runnable {

        /**
         * Run.
         */
        @Override
        public void run() {
            String[] importantInfo = {
                    "Mares eat oats",
                    "Does eat oats",
                    "Little lambs eat ivy",
                    "A kid will eat ivy too"
            };

            try {
                for (int i = 0; i < importantInfo.length; i++) {
                    Thread.sleep(4000);
                    threadMessage(importantInfo[i]);
                }
            } catch (InterruptedException e) {
                threadMessage("I wasn't done..");
            }
        }
    }
}
