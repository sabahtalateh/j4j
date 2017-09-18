package com.sabahtalateh.j4j.multithreading.wait_notify.thread_pool.Work;

import java.util.UUID;

/**
 * Work.
 */
public abstract class Work {

    private final String workId = UUID.randomUUID().toString().substring(0, 4);

    /**
     * @return work id.
     */
    String getWorkId() {
        return workId;
    }

    /**
     * Do work.
     */
    public abstract void doWork();
}
