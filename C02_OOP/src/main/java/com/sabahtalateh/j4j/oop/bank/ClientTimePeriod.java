package com.sabahtalateh.j4j.oop.bank;

import com.sabahtalateh.j4j.oop.bank.time.Time;

/**
 * ClientTimePeriod.
 */
public class ClientTimePeriod {
    private Client client;

    private Time came;

    private Time left;

    /**
     * @param client client.
     * @param came   when came.
     * @param left   when left.
     */
    public ClientTimePeriod(Client client, Time came, Time left) {
        this.client = client;
        this.came = came;
        this.left = left;
    }

    /**
     * @return string.
     */
    @Override
    public String toString() {
        return "ClientTimePeriod{client=" + client + ", came=" + came + ", left=" + left + '}';
    }

    /**
     * @return time.
     */
    public Time getCame() {
        return came;
    }

    /**
     * @return time.
     */
    public Time getLeft() {
        return left;
    }
}
