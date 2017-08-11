package com.sabahtalateh.j4j.collections_advanced.bookstore;

import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;

/**
 * Store.
 */
public class Store {
    Map<String, OrderSet> store = new TreeMap<>();

    /**
     * @param productName product name.
     * @param orderType order type.
     * @param amount amount.
     * @param price price.
     */
    public void addOrder(String productName, OrderType orderType, int amount, double price) {
        if (!store.containsKey(productName)) {
            store.put(productName, new OrderSet());
        }
        store.get(productName).addOrder(orderType, price, amount);
    }

    /**
     * Run biding process.
     */
    public void runBid() {
        this.store.forEach((product, orders) -> orders.runBid());
    }

    /**
     * @param out where to print report.
     */
    public void printReport(PrintStream out) {
        this.store.forEach(((product, orderSet) -> {
            out.printf("Order book: %s%n", product);
            orderSet.printReport(out);
            out.printf("%n");
        }));
    }
}


