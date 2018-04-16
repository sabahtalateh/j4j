package com.sabahtalateh.j4j.jdbc.test_task;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;

/**
 * ExpirableStore.
 */
class ExpirableStore implements StoreInterface {

    private List<Event> events = new ArrayList<>();

    private final TemporalAmount ttl;

    /**
     * @param ttl ttl of store elements since them were placed in it.
     */
    ExpirableStore(TemporalAmount ttl) {
        this.ttl = ttl;
    }

    /**
     * @param e event to add to store.
     * @return boolean.
     */
    public boolean add(Event e) {
        return events.add(e);
    }

    /**
     * @return store size.
     */
    public long size() {
        return events.size();
    }

    /**
     * @return list of expired events.
     */
    List<Event> expireEvents() {
        return expireEvents(LocalDateTime.now());
    }

    /**
     * @param now set now to this date, ttl will be added to event fired date-time and if event is
     *            older than the result it will be expired (generally for testing purposes).
     * @return list of expired events.
     */
    List<Event> expireEvents(LocalDateTime now) {
        List<Event> expiredEvents = new ArrayList<>();

        for (Event event : events) {
            LocalDateTime expiration = event.getFiredAt().plus(ttl);
            boolean expired = expiration.isBefore(now);
            if (expired) {
                expiredEvents.add(event);
            }
        }

        events.removeAll(expiredEvents);

        return expiredEvents;
    }
}
