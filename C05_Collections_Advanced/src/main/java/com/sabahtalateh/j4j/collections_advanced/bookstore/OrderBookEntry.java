package com.sabahtalateh.j4j.collections_advanced.bookstore;

/**
 * OrderBookEntry.
 */
public class OrderBookEntry {
    String productName;
    OrderType type;
    double price;
    int volume;

    /**
     * @param productName product name.
     * @param type        order type.
     * @param price       price.
     * @param volume      volume.
     */
    OrderBookEntry(String productName, OrderType type, double price, int volume) {
        this.productName = productName;
        this.type = type;
        this.price = price;
        this.volume = volume;
    }
}
