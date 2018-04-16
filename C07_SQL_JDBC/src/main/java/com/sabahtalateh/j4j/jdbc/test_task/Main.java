package com.sabahtalateh.j4j.jdbc.test_task;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * mvn clean install && java -cp "target/dependency/*:target/C07_SQL_JDBC-2.0.jar" com.sabahtalateh.j4j.jdbc.test_task.Main
 * <p>
 * Main.
 */
public class Main {
    /**
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        Map<String, Duration> durationMap = new HashMap<String, Duration>() {{
            put("one_second", Duration.ofSeconds(1));
            put("ten_second", Duration.ofSeconds(5));
            put("one_year", Duration.ofDays(365));
        }};
        StatisticStore store = StatisticStores.makeStore(durationMap);

        printStatistics(store);

        Thread.sleep(1000);

        System.out.println("Add two events.");

        store.add(new Event());
        store.add(new Event());

        printStatistics(store);

        System.out.println("Wait for 2 seconds.");

        Thread.sleep(2000);

        printStatistics(store);

        System.out.println("Add three more events.");

        store.add(new Event());
        store.add(new Event());
        store.add(new Event());

        printStatistics(store);

        System.out.println("Wait for 4 seconds.");

        Thread.sleep(4000);

        printStatistics(store);

        System.out.println("Wait for 2 seconds.");

        Thread.sleep(2000);

        printStatistics(store);

        System.exit(0);
    }

    /**
     * Print statistics to the screen.
     *
     * @param store store.
     */
    private static void printStatistics(StatisticStore store) {
        System.out.printf("%nLast second: %s events%n"
                        + "Last ten seconds: %s events%n"
                        + "Last one year: %s events%n%n",
                store.countEventsForPeriod("one_second"),
                store.countEventsForPeriod("ten_second"),
                store.countEventsForPeriod("one_year")
        );
    }
}
