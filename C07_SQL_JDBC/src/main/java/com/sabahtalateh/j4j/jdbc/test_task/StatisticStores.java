package com.sabahtalateh.j4j.jdbc.test_task;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * StatisticStores.
 */
class StatisticStores {

    private static final long DEFAULT_PERIOD = 1L;
    private static final TimeUnit DEFAULT_PERIOD_UNIT = TimeUnit.SECONDS;

    /**
     * @param periods periods to gather statics.
     * @return store.
     */
    static StatisticStore makeStore(Map<String, Duration> periods) {
        StatisticStore store = new StatisticStore(periods);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(store::expireStores, 0, DEFAULT_PERIOD, DEFAULT_PERIOD_UNIT);

        return store;
    }

    /**
     * Adjust expiration thread period if needed.
     *
     * @param periods          periods to gather statics.
     * @param expirationPeriod store expiration period.
     * @param expirationUnit   store expiration period unit.
     * @return store.
     */
    static StatisticStore makeStore(Map<String, Duration> periods, long expirationPeriod, TimeUnit expirationUnit) {
        StatisticStore store = new StatisticStore(periods);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(store::expireStores, 0, expirationPeriod, expirationUnit);

        return store;
    }
}
