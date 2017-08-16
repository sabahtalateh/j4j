package com.sabahtalateh.j4j.multithreading.jmm.habr;

/**
 * ReorderingRun.
 */
public class ReorderingRun {

    /**
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        ReorderingSample sample = new ReorderingSample();

        new Thread(new ReorderingRunnableCheck(sample)).start();
        Thread.sleep(500);
        new Thread(new ReorderingRunnableSet(sample)).start();


    }

    /**
     * ReorderingRunnableCheck.
     */
    private static class ReorderingRunnableCheck implements Runnable {

        ReorderingSample sample;

        /**
         * @param sample sample.
         */
        ReorderingRunnableCheck(ReorderingSample sample) {
            this.sample = sample;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            while (!sample.second) {
                System.out.printf("[%s] %s", Thread.currentThread().getName(), sample);
            }
            assert sample.first;
            System.out.printf("[%s] %s", Thread.currentThread().getName(), sample);
        }
    }

    /**
     * ReorderingRunnableCheck.
     */
    private static class ReorderingRunnableSet implements Runnable {

        ReorderingSample sample;

        /**
         * @param sample sample.
         */
        ReorderingRunnableSet(ReorderingSample sample) {
            this.sample = sample;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            sample.setValues();
        }
    }
}
