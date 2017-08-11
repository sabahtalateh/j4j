package com.sabahtalateh.j4j.collections_advanced.bookstore;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * OrderSetTest.
 */
public class OrderSetTest {
    @Test
    public void whenAddOrdersWithSamePriceItsAmountIncreases() throws Exception {
        OrderSet orders = new OrderSet();
        orders.addOrder(OrderType.BID, 100.0, 10);
        orders.addOrder(OrderType.BID, 100.0, 5);
        assertThat(orders.getOrderAmount(OrderType.BID, 100.0), is(15));

        orders.addOrder(OrderType.BID, 100.0, 85);
        assertThat(orders.getOrderAmount(OrderType.BID, 100.0), is(100));

        orders.addOrder(OrderType.ASK, 100.0, 10);
        orders.addOrder(OrderType.ASK, 100.0, 5);
        assertThat(orders.getOrderAmount(OrderType.ASK, 100.0), is(15));

        orders.addOrder(OrderType.ASK, 100.0, 85);
        assertThat(orders.getOrderAmount(OrderType.ASK, 100.0), is(100));
    }

    @Test
    public void whenBidThenChooseAskWithClosestPrice() throws Exception {
        OrderSet orders = new OrderSet();
        orders.addOrder(OrderType.BID, 100.0, 15);
        orders.addOrder(OrderType.BID, 99.0, 10);

        orders.addOrder(OrderType.ASK, 100.0, 10);
        orders.addOrder(OrderType.ASK, 98.9, 15);

        orders.runBid();

        assertThat(orders.getOrderAmount(OrderType.BID, 100.0), is(5));
        assertThat(orders.getOrderAmount(OrderType.BID, 99.0), is(0));

        assertThat(orders.getOrderAmount(OrderType.ASK, 100.0), is(0));
        assertThat(orders.getOrderAmount(OrderType.ASK, 98.9), is(5));
    }
}
