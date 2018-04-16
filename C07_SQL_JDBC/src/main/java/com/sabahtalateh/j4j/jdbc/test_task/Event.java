package com.sabahtalateh.j4j.jdbc.test_task;

import java.time.LocalDateTime;

/**
 * Event.
 */
public class Event {
    private final LocalDateTime firedAt;

    /**
     * Create event.
     */
    public Event() {
        this.firedAt = LocalDateTime.now();
    }

    /**
     * Create event with time.
     *
     * @param firedAt time when event was fired.
     */
    public Event(final LocalDateTime firedAt) {
        this.firedAt = firedAt;
    }

    /**
     * @return time when event was fired.
     */
    public LocalDateTime getFiredAt() {
        return firedAt;
    }
}
