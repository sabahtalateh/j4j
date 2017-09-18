package com.sabahtalateh.j4j.multithreading.accounts;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

/**
 * AccountStorage.
 */
@ThreadSafe
public class AccountStorage {
    private final ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    /**
     * @param account account.
     * @return is added.
     */
    public boolean add(Account account) {
        return accounts.putIfAbsent(account.getId(), account) != account;
    }

    /**
     * @param newAccount new account.
     * @return is updated.
     */
    public synchronized boolean update(Account newAccount) {
        boolean updated = false;
        Account toUpdate = accounts.get(newAccount.getId());
        if (toUpdate != null) {
            synchronized (toUpdate) {
                synchronized (newAccount) {
                    updated = accounts.replace(newAccount.getId(), newAccount) != null;
                }
            }
        }
        return updated;
    }

    /**
     * @param account account to delete.
     * @return is deleted.
     */
    public synchronized boolean delete(Account account) {
        boolean deleted = false;

        Account deletingAccount = accounts.get(account.getId());

        if (deletingAccount != null) {
            synchronized (deletingAccount) {
                accounts.remove(deletingAccount.getId());
            }
            deleted = true;
        }

        return deleted;
    }

    /**
     * @param fromId from
     * @param toId   to
     * @param amount amount
     * @return transfered
     */
    public synchronized boolean transfer(String fromId, String toId, long amount) {
        boolean transfered = false;
        Account from = accounts.get(fromId);
        Account to = accounts.get(toId);

        if (from != null && to != null && from.getAmount() >= amount) {
            if (!fromId.equals(toId)) {
                Account first, second;
                boolean fromFirst = true;

                if (from.getId().compareTo(to.getId()) > 0) {
                    first = from;
                    second = to;
                } else {
                    first = to;
                    second = from;
                    fromFirst = false;
                }

                synchronized (first) {
                    synchronized (second) {
                        if (fromFirst) {
                            first.subtractAmount(amount);
                            second.addAmount(amount);
                        } else {
                            second.subtractAmount(amount);
                            first.addAmount(amount);
                        }
                    }
                }
            }
            transfered = true;
        }
        return transfered;
    }

    /**
     * @param id id.
     * @return optional account.
     */
    public Account get(String id) {
        return accounts.get(id);
    }
}
