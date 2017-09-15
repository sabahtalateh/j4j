package com.sabahtalateh.j4j.multithreading.wait_notify.thread_pool;

import com.sabahtalateh.j4j.multithreading.wait_notify.thread_pool.Work.Work;

/**
 * Worker.
 */
public class Worker extends Thread {

    private volatile Work work = null;

    private final Object lock = new Object();

    /**
     * @param work work.
     * @throws InterruptedException exception.
     */
    public void setWork(Work work) throws InterruptedException {
        synchronized (lock) {
            while (this.work != null) {
                lock.wait();
            }
            this.work = work;
            lock.notifyAll();
        }
    }

    /**
     * @return has work.
     */
    public boolean hasWork() {
        return work != null;
    }

    /**
     * Run.
     */
    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                while (this.work == null) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.work.doWork();
                this.work = null;
                lock.notifyAll();
            }
        }
    }
}
