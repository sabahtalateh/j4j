package com.sabahtalateh.j4j.multithreading.jmm.concurrency;

/**
 * Concurrency.
 */
public class Concurrency {

    /**
     * @param args args.
     */
    public static void main(String[] args) {
        SharedValue sharedValue = new SharedValue();

        new Thread(new Concurrent(sharedValue)).start();
        new Thread(new Concurrent(sharedValue)).start();
    }

    /**
     * Concurrent.
     */
    private static class Concurrent implements Runnable {

        final SharedValue sharedValue;

        /**
         * @param sharedValue shared value.
         */
        Concurrent(SharedValue sharedValue) {
            this.sharedValue = sharedValue;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            this.sharedValue.value += 10;
            System.out.printf("[%s] %s%n", Thread.currentThread().getName(), this.sharedValue.value);
        }
    }

    /**
     * SharedValue.
     */
    private static class SharedValue {
        public Integer value = -10;
    }
}
