package com.sabahtalateh.j4j.multithreading.wait_notify.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Lock.
 */
public class Lock {

    private volatile Thread locker;

    private final boolean diagnosticMode;

    private final String lockId = UUID.randomUUID().toString().substring(0, 4);

    private volatile int lockersCount = 0;

    private long lastMessage = System.currentTimeMillis();

    private final long messagesInterval = 5000L;

    private final List<Thread> waiters = new ArrayList<>();

    /**
     * Lock.
     */
    public Lock() {
        this(false);
    }

    /**
     * @param diagnosticMode diagnostic mode.
     */
    public Lock(boolean diagnosticMode) {
        this.diagnosticMode = diagnosticMode;
        if (this.diagnosticMode) {
            runDiagnostic();
        }
    }

    /**
     * Lock.
     */
    public synchronized void lock() {
        if (lockersCount == 0) {
            locker = Thread.currentThread();
            lockersCount++;
            locker = Thread.currentThread();
            waiters.remove(locker);
            if (diagnosticMode) {
                System.out.printf("[%s] acquired (%d locks) by [%s].%n", lockId, lockersCount, locker.getName());
            }
        } else if (lockersCount > 0 && locker == Thread.currentThread()) {
            lockersCount++;
            locker = Thread.currentThread();
            waiters.remove(locker);
            if (diagnosticMode) {
                System.out.printf("[%s] acquired (%d locks) by [%s].%n", lockId, lockersCount, locker.getName());
            }
        } else {
            try {
                waiters.add(Thread.currentThread());
                this.wait();
                this.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Unlock.
     */
    public synchronized void unlock() {
        if (lockersCount == 0 || locker != Thread.currentThread()) {
            throw new IllegalMonitorStateException("No lockers.");
        }

        lockersCount--;
        if (diagnosticMode) {
            System.out.printf("[%s] released (%d remains) by [%s].%n", lockId, lockersCount, Thread.currentThread().getName());
        }
        if (lockersCount == 0) {
            locker = null;
            this.notify();
        }
    }

    /**
     * Diagnostic.
     */
    private void runDiagnostic() {
        new Thread(() -> {
            while (true) {
                if (System.currentTimeMillis() - lastMessage > messagesInterval) {
                    System.out.println("======");
                    String waitersString = waiters.stream()
                            .map(Thread::getName)
                            .reduce((w1, w2) -> String.format("%s, %s", w1, w2))
                            .orElse("NO WAITERS.");
                    System.out.printf("Lock       [%s]%n", lockId);
                    System.out.printf("Locked by: [%s]%n", locker != null ? locker.getName() : "Nobody");
                    System.out.printf("Waiters:   [%s]%n", waitersString);

                    System.out.println("-");
                    lastMessage = System.currentTimeMillis();
                }
            }
        }).start();
    }
}
