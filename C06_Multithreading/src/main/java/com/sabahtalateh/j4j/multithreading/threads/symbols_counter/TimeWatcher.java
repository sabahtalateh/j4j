package com.sabahtalateh.j4j.multithreading.threads.symbols_counter;

/**
 * TimeWatcher.
 */
public class TimeWatcher implements Runnable {

    private final long maxWorkingTime;

    private boolean timeIsOver = false;

    /**
     * @param maxCountingTime max working time.
     */
    public TimeWatcher(long maxCountingTime) {
        this.maxWorkingTime = maxCountingTime;
    }

    /**
     * Run thread.
     */
    @Override
    public void run() {
        long finishTime = System.currentTimeMillis() + this.maxWorkingTime;

        while (true) {
            if (Thread.interrupted()) {
                return;
            }

            if (System.currentTimeMillis() > finishTime) {
                this.timeIsOver = true;
                return;
            }
        }
    }

    /**
     * @return is time over.
     */
    public boolean isTimeOver() {
        return timeIsOver;
    }
}
