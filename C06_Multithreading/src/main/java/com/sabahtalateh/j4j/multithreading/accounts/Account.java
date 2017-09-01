package com.sabahtalateh.j4j.multithreading.accounts;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Account.
 */
public class Account {
    private final String id;
    private final String ownerName;
    private long amount = 0;
    public Lock lock = new ReentrantLock();

    /**
     * @param id id.
     */
    public Account(String id) {
        this(id, null, 0);
    }

    /**
     * @param id        id.
     * @param ownerName owner name.
     */
    public Account(String id, String ownerName) {
        this(id, ownerName, 0);
    }

    /**
     * @param id        id.
     * @param ownerName owner name.
     * @param amount    amount.
     */
    public Account(String id, String ownerName, long amount) {
        this.id = id;
        this.ownerName = ownerName;
        this.amount = amount;
    }

    /**
     * @return id.
     */
    public String getId() {
        return id;
    }

    /**
     * @return owner name.
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * @return amount.
     */
    public long getAmount() {
        return amount;
    }

    /**
     * @param amount amount.
     * @return new amount.
     */
    public long addAmount(long amount) {
        return this.amount += amount;
    }

    /**
     * @param amount amount.
     * @return new amount.
     */
    public long subtractAmount(long amount) {
        return this.amount -= amount;
    }

    /**
     * @param o object.
     * @return result.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return id != null ? id.equals(account.id) : account.id == null;
    }

    /**
     * @return hash code.
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * @return string.
     */
    @Override
    public String toString() {
        return "Account{id='" + id + "\', ownerName='" + ownerName + "\', amount=" + amount + '}';
    }
}
