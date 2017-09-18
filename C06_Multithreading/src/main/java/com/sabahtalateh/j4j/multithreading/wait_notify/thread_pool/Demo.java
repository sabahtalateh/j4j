package com.sabahtalateh.j4j.multithreading.wait_notify.thread_pool;

import com.sabahtalateh.j4j.multithreading.wait_notify.thread_pool.Work.Sleep;
import com.sabahtalateh.j4j.multithreading.wait_notify.thread_pool.Work.SleepRandomTime;

/**
 * Demo.
 */
public class Demo {
    /**
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        Pool pool = new Pool();

        pool.addWork(new SleepRandomTime());
        pool.addWork(new SleepRandomTime());
        pool.addWork(new SleepRandomTime());
        pool.addWork(new SleepRandomTime());
        pool.addWork(new Sleep(10000));
        pool.addWork(new SleepRandomTime());
        pool.addWork(new SleepRandomTime());
        pool.addWork(new SleepRandomTime());
        pool.addWork(new SleepRandomTime());
        pool.addWork(new SleepRandomTime());
        pool.addWork(new SleepRandomTime());
        pool.addWork(new Sleep(10000));
        pool.addWork(new SleepRandomTime());
        pool.addWork(new SleepRandomTime());
        pool.addWork(new SleepRandomTime());
        pool.addWork(new Sleep(10000));
        pool.addWork(new SleepRandomTime());
        pool.addWork(new Sleep(10000));
        pool.addWork(new SleepRandomTime());
        pool.addWork(new Sleep(10000));
        pool.addWork(new SleepRandomTime());
        pool.addWork(new Sleep(10000));
        pool.addWork(new SleepRandomTime());
        pool.addWork(new SleepRandomTime());
        pool.addWork(new SleepRandomTime());
        pool.addWork(new Sleep(10000));
        pool.start();
        pool.join();
    }
}
