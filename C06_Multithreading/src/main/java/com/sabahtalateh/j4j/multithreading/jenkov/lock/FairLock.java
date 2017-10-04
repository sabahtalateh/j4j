package com.sabahtalateh.j4j.multithreading.jenkov.lock;


import java.util.ArrayList;
import java.util.List;

/**
 * FairLock.
 */
public class FairLock {
    private boolean isLocked = false;
    private Thread lockingThread = null;
    private List<QueueObject> waitingThreads = new ArrayList<QueueObject>();

    /**
     * Lock.
     */
    public void lock() {
        QueueObject queueObject = new QueueObject();
        boolean isLockedForThisThread = true;

        synchronized (this) {
            waitingThreads.add(queueObject);
        }

        while (isLockedForThisThread) {
            synchronized (this) {
                isLockedForThisThread = isLocked || waitingThreads.get(0) != queueObject;
                if (!isLockedForThisThread) {
                    isLocked = true;
                    waitingThreads.remove(queueObject);
                }
            }
        }
    }
}
