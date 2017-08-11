package com.sabahtalateh.j4j.collections_advanced.bookstore;

import java.io.PrintStream;
import java.util.*;

/**
 * OrderSet.
 */
public class OrderSet {
    private Map<Double, Integer> bids = new TreeMap<>(Comparator.reverseOrder());
    private Map<Double, Integer> asks = new TreeMap<>(Comparator.reverseOrder());

    /**
     * @param type   order type.
     * @param price  price.
     * @param amount amount.
     */
    public void addOrder(OrderType type, Double price, Integer amount) {
        Map<Double, Integer> target = type == OrderType.ASK ? this.asks : this.bids;
        if (target.containsKey(price)) {
            target.put(price, target.get(price) + amount);
        } else {
            target.put(price, amount);
        }
    }

    /**
     * Run bidding process.
     */
    public void runBid() {
        for (Map.Entry<Double, Integer> bid : this.bids.entrySet()) {
            Optional<Map.Entry<Double, Integer>> askEntry = this.asks.entrySet().stream()
                    .filter(ask -> bid.getKey() >= ask.getKey() && ask.getValue() != 0)
                    .findFirst();

            if (askEntry.isPresent()) {
                if (askEntry.get().getValue() > bid.getValue()) {
                    askEntry.get().setValue(askEntry.get().getValue() - bid.getValue());
                    bid.setValue(0);
                } else if (askEntry.get().getValue() < bid.getValue()) {
                    bid.setValue(bid.getValue() - askEntry.get().getValue());
                    askEntry.get().setValue(0);
                } else {
                    askEntry.get().setValue(0);
                    bid.setValue(0);
                }
            }
        }
    }

    /**
     * @param out where to print report.
     */
    public void printReport(PrintStream out) {
        out.printf("%nBID\t\t\t\tASK%n%n");

        Map<Double, Integer> sortedAsks = new TreeMap<>(Double::compareTo);
        sortedAsks.putAll(this.asks);

        Iterator<Map.Entry<Double, Integer>> bidsIterator = this.bids.entrySet().iterator();
        Iterator<Map.Entry<Double, Integer>> asksIterator = sortedAsks.entrySet().iterator();

        while (bidsIterator.hasNext() || asksIterator.hasNext()) {
            if (bidsIterator.hasNext()) {
                Map.Entry<Double, Integer> bid = bidsIterator.next();
                out.printf("%d@%.2f", bid.getValue(), bid.getKey());
            } else {
                out.printf("\t");
            }

            out.printf("\t\t");

            if (asksIterator.hasNext()) {
                Map.Entry<Double, Integer> ask = asksIterator.next();
                out.printf("%d@%.2f", ask.getValue(), ask.getKey());
            }

            out.printf("%n");
        }
    }

    /**
     * @param orderType order type.
     * @param price     price.
     * @return amount.
     */
    public int getOrderAmount(OrderType orderType, Double price) {
        Map<Double, Integer> target = orderType == OrderType.ASK ? this.asks : this.bids;
        return target.get(price);
    }

    /**
     * @return string.
     */
    @Override
    public String toString() {
        return "OrderSet{bids=" + bids + ", asks=" + asks + '}';
    }
}
