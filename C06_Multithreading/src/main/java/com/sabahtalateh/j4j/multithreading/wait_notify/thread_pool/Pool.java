package com.sabahtalateh.j4j.multithreading.wait_notify.thread_pool;

import com.sabahtalateh.j4j.multithreading.wait_notify.thread_pool.Work.Work;

import java.util.LinkedList;

/**
 * Pool.
 */
public class Pool extends Thread {

    private static final long DIAGNOSTIC_MESSAGE_TIME = 4000;

    //    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int POOL_SIZE = 4;

    private final Worker[] workers = new Worker[POOL_SIZE];
    private final LinkedList<Work> works = new LinkedList<>();

    private long lastDiagnosticMessageWrittenAt = System.currentTimeMillis();
    private int worksDone = 0;

    /**
     * Pool.
     */
    public Pool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            workers[i] = new Worker();
            workers[i].start();
        }
    }

    /**
     * @param work work.
     */
    public void addWork(Work work) {
        works.add(work);
    }

    /**
     * Run.
     */
    @Override
    public synchronized void run() {
        runDiagnosticMessagesPrinting();
        while (true) {
            int readyWorkerIndex = getReadyWorkerIndex();
            if (readyWorkerIndex != -1 && works.size() > 0) {
                System.out.println(readyWorkerIndex);
                try {
                    workers[readyWorkerIndex].setWork(works.removeFirst());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                worksDone++;
            }
        }
    }

    /**
     * @return index.
     */
    private int getReadyWorkerIndex() {
        int readyWorkerIndex = -1;
        for (int i = 0; i < POOL_SIZE; i++) {
            if (!workers[i].hasWork()) {
                readyWorkerIndex = i;
                break;
            }
        }
        return readyWorkerIndex;
    }

    /**
     * Run.
     */
    private synchronized void runDiagnosticMessagesPrinting() {
        new Thread(() -> {
            while (true) {
                long millis = System.currentTimeMillis();
                if (millis - lastDiagnosticMessageWrittenAt > DIAGNOSTIC_MESSAGE_TIME) {
                    lastDiagnosticMessageWrittenAt = millis;
                    int busy = 0;
                    int ready = 0;
                    for (int i = 0; i < POOL_SIZE; i++) {
                        if (!workers[i].hasWork()) {
                            ready++;
                        }
                    }
                    busy = POOL_SIZE - ready;
                    System.out.printf("DIAGNOSTIC:%n"
                                    + "DONE:  [%s works done]%n"
                                    + "READY: [%s workers]%n"
                                    + "BUSY:  [%s workers]%n",
                            worksDone, ready, busy);
                }
            }
        }).start();
    }
}
