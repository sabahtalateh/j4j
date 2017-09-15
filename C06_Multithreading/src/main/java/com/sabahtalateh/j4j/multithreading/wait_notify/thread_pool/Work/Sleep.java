package com.sabahtalateh.j4j.multithreading.wait_notify.thread_pool.Work;

/**
 * SleepRandomTime.
 */
public class Sleep extends Work {

    private final long sleepTime;

    /**
     * @param sleepTime sleep time.
     */
    public Sleep(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    /**
     * Do work.
     */
    @Override
    public void doWork() {
        try {
            System.out.printf("[%s] Start sleeping for %.2f seconds.%n", getWorkId(), sleepTime / 1000.0);
            Thread.sleep(sleepTime);
            System.out.printf("[%s] Done.%n", getWorkId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
