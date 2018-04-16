package com.sabahtalateh.j4j.jdbc.test_task;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ExpirableStoreTest.
 */
public class ExpirableStoreTest {
    @Test
    public void testZeroElementsStoreReturnsZeroSize() throws Exception {
        TemporalAmount ttl = Duration.ofMinutes(1);
        ExpirableStore store = new ExpirableStore(ttl);

        assertThat(store.size(), is(0L));
    }

    @Test
    public void testOneElementStoreReturnsOneSize() throws Exception {
        TemporalAmount ttl = Duration.ofMinutes(1);
        ExpirableStore store = new ExpirableStore(ttl);
        store.add(new Event());

        assertThat(store.size(), is(1L));
    }

    @Test
    public void testExpiredEventRemovedFromStore() throws Exception {
        TemporalAmount ttl = Duration.ofMinutes(1);
        ExpirableStore store = new ExpirableStore(ttl);
        LocalDateTime septemberIsBurningMurdererIsCrying = LocalDateTime.of(2007, 9, 1, 0, 0, 0);
        Event expiredEvent = new Event(septemberIsBurningMurdererIsCrying);
        store.add(expiredEvent);

        assertThat(store.size(), is(1L));

        LocalDateTime storeCurrentTime = LocalDateTime.of(2007, 9, 1, 0, 1, 1);
        List<Event> expiredActual = store.expireEvents(storeCurrentTime);
        List<Event> expiredExpected = new ArrayList<Event>() {{
            add(expiredEvent);
        }};

        assertThat(store.size(), is(0L));
        assertThat(expiredActual, is(expiredExpected));
    }

    @Test
    public void onlyExpiredEventsAreRemovedFromStore() throws Exception {
        // Current time of the storage is 2007-09-01 00:00:00
        // Events TTL is 1 minute.
        LocalDateTime storeCurrentTime = LocalDateTime.of(2007, 9, 1, 0, 2, 1);

        TemporalAmount ttl = Duration.ofMinutes(1);
        ExpirableStore store = new ExpirableStore(ttl);


        Event expiredEvent0 = new Event(LocalDateTime.of(2007, 8, 29, 0, 0, 0));
        Event expiredEvent1 = new Event(LocalDateTime.of(2007, 8, 30, 23, 59, 5));
        Event expiredEvent2 = new Event(LocalDateTime.of(2007, 9, 1, 0, 1, 0));
        Event expiredEvent3 = new Event(LocalDateTime.of(2007, 9, 1, 0, 0, 0));

        Event notExpiredEvent0 = new Event(LocalDateTime.of(2007, 9, 1, 0, 1, 20));
        Event notExpiredEvent1 = new Event(LocalDateTime.of(2007, 9, 1, 0, 1, 45));

        store.add(expiredEvent0);
        store.add(expiredEvent1);
        store.add(expiredEvent2);
        store.add(expiredEvent3);

        store.add(notExpiredEvent0);
        store.add(notExpiredEvent1);

        assertThat(store.size(), is(6L));

        List<Event> expiredActual = store.expireEvents(storeCurrentTime);

        List<Event> expireExpected = new ArrayList<Event>() {{
            add(expiredEvent0);
            add(expiredEvent1);
            add(expiredEvent2);
            add(expiredEvent3);
        }};

        assertThat(store.size(), is(2L));
        assertThat(expiredActual, is(expireExpected));
    }
}