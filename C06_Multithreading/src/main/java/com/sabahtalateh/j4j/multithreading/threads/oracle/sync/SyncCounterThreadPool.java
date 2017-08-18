package com.sabahtalateh.j4j.multithreading.threads.oracle.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * SyncCounterThreadPool.
 */
public class SyncCounterThreadPool {
    private static final int THREAD_COUNTS = 100;

    /**
     * @param args args.
     * @throws InterruptedException exception
     */
    public static void main(String[] args) throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNTS);
        final CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNTS);
        final Counter counter = Counter.newInstance();

        executeThreads(threadPool, countDownLatch, counter);

        countDownLatch.await();
        threadPool.shutdown();

        System.out.println(counter.getCount());
    }

    /**
     * @param threadPool     pool.
     * @param countDownLatch latch.
     * @param counter        counter.
     */
    private static void executeThreads(
            ExecutorService threadPool,
            CountDownLatch countDownLatch,
            Counter counter
    ) {
        for (int i = 0; i < THREAD_COUNTS; i++) {
            threadPool.execute(new IncrementTask(counter, countDownLatch));
        }
    }

    /**
     * Counter.
     */
    private static class Counter {
        private int count;

        /**
         * Increment.
         */
        synchronized void increment() {
            count = count + 1;
        }

        /**
         * @return count.
         */
        synchronized int getCount() {
            return count;
        }

        /**
         * @return instance.
         */
        static Counter newInstance() {
            return new Counter();
        }
    }

    /**
     * IncrementTask.
     */
    private static class IncrementTask implements Runnable {
        private final Counter counter;
        private final CountDownLatch countDownLatch;

        /**
         * @param counter counter.
         * @param countDownLatch latch.
         */
        IncrementTask(Counter counter, CountDownLatch countDownLatch) {
            this.counter = counter;
            this.countDownLatch = countDownLatch;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
                counter.increment();
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}