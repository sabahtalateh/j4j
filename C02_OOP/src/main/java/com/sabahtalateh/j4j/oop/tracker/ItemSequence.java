package com.sabahtalateh.j4j.oop.tracker;

import java.util.UUID;

/**
 * ItemSequence.
 */
class ItemSequence {
    /**
     * @return next sequence value.
     */
    public String next() {
        return UUID.randomUUID().toString();
    }
}
