package com.sabahtalateh.j4j.jdbc.test_task;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * StatisticStore.
 * <p>
 * 4 stores will be created:
 * 1 - Last minute store.
 * 2 - Last hour store.
 * 3 - Last day store.
 * 4 - Very long time store.
 */
class StatisticStore {

    private Map<String, ExpirableStore> stores = new HashMap<>();

    /**
     * @param periods periods to gather statics, periods has names to retrieve statistic.
     */
    StatisticStore(Map<String, Duration> periods) {
        for (Map.Entry<String, Duration> period : periods.entrySet()) {
            stores.put(period.getKey(), new ExpirableStore(period.getValue()));
        }
    }

    /**
     * @param e event to add.
     */
    void add(Event e) {
        stores.forEach((period, store) -> store.add(e));
    }

    /**
     * Expire all stores.
     */
    void expireStores() {
        stores.forEach((period, store) -> store.expireEvents());
    }

    /**
     * @param periodName name of period to gather statistic.
     * @return count events for period.
     */
    long countEventsForPeriod(String periodName) {
        if (stores.containsKey(periodName)) {
            return stores.get(periodName).size();
        }
        throw new NoSuchPeriodException("No period defined with name " + periodName);
    }
}
