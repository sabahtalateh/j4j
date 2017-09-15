package com.sabahtalateh.j4j.multithreading.wait_notify.thread_pool.Work;

import static java.lang.Math.random;

/**
 * SleepRandomTime.
 */
public class SleepRandomTime extends Work {
    /**
     * Do work.
     */
    @Override
    public void doWork() {
        try {
            synchronized (this) {
                long sleepTime = (long) (random() * 2000);
                System.out.printf("[%s] Start sleeping for %.2f seconds.%n", getWorkId(), sleepTime / 1000.0);
                Thread.sleep((long) (random() * 2000));
                System.out.printf("[%s] Done.%n", getWorkId());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
