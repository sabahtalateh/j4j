package com.sabahtalateh.servlets.users.store;

import java.util.concurrent.atomic.AtomicLong;

/**
 * IdSequence.
 */
public class IdSequence {
    private AtomicLong currentId = new AtomicLong(0);

    /**
     * @return next id.
     */
    public long next() {
        return currentId.getAndIncrement();
    }
}
