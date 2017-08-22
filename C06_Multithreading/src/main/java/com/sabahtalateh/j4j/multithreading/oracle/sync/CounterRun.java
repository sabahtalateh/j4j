package com.sabahtalateh.j4j.multithreading.oracle.sync;

/**
 * CounterRun.
 */
public class CounterRun {

    /**
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println(increment100());
    }

    /**
     * @return number.
     * @throws InterruptedException exception.
     */
    public static int increment100() throws InterruptedException {
        final int iters = 100;

        Counter counter = new Counter();

        Thread[] threads = new Thread[iters];

        for (int i = 0; i < iters; i++) {
            threads[i] = new Thread(new Incrementer(counter));
        }

        for (int i = 0; i < iters; i++) {
            threads[i].start();
        }

        for (int i = 0; i < iters; i++) {
            threads[i].join();
        }

        return counter.getValue();
    }

    /**
     * Counter.
     */
    private static class Counter {
        int c = 0;

        /**
         * Increment.
         */
        void increment() {
            int oldC = this.c;

            this.c = (int) Math.pow(33, 3);
            this.c = (int) Math.pow(33, 2);
            this.c = (int) Math.pow(33, 4);
            this.c = (int) Math.pow(33, 5);
            this.c = (int) Math.pow(33, 6);
            this.c = 19 + 78;
            this.c = 65 + 999999;
            this.c = this.c * 23;

            this.c = oldC + 1;
        }

        /**
         * @return value.
         */
        int getValue() {
            return this.c;
        }
    }

    /**
     * Incrementer.
     */
    private static class Incrementer implements Runnable {

        Counter counter;

        /**
         * @param counter counter.
         */
        Incrementer(Counter counter) {
            this.counter = counter;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.counter.increment();
        }
    }
}
