package com.sabahtalateh.j4j.multithreading.accounts;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * AccountStorage.
 */
public class AccountStorage {
    private final Map<String, Account> accounts = new HashMap<>();

    /**
     * @param account account.
     * @return is added.
     */
    public synchronized boolean add(Account account) {
        boolean added = false;
        if (!accounts.containsKey(account.getId())) {
            accounts.put(account.getId(), account);
            added = true;
        }
        return added;
    }

    /**
     * @param account account.
     * @return is updated.
     */
    public synchronized boolean update(Account account) {
        boolean updated = false;
        if (accounts.containsKey(account.getId())) {
            accounts.put(account.getId(), account);
            updated = true;
        }
        return updated;
    }

    /**
     * @param account account.
     * @return is deleted.
     */
    public synchronized boolean delete(Account account) {
        boolean deleted = false;
        if (accounts.containsKey(account.getId())) {
            accounts.remove(account.getId());
            deleted = true;
        }
        return deleted;
    }

    /**
     * @param fromId from.
     * @param toId   to.
     * @param amount amount.
     * @return is transfered.
     */
    public boolean transfer(String fromId, String toId, long amount) {
        boolean transfered = false;

        Account from = accounts.get(fromId);
        Account to = accounts.get(toId);

        if (from != null && to != null && this.tryLockAccounts(from, to) && from.getAmount() >= amount) {
            try {
                from.subtractAmount(amount);
                to.addAmount(amount);
                transfered = true;
            } finally {
                this.unlockAccounts(from, to);
            }
        }

        return transfered;
    }

    /**
     * @param id id.
     * @return optional account.
     */
    public synchronized Optional<Account> findByAccountId(String id) {
        Account account = accounts.get(id);
        return account == null ? Optional.empty() : Optional.of(account);
    }

    /**
     * True if both lock was acquired.
     * <p>
     * * Arguments order when method calls should be the same as in {@see unlockAccounts}.
     * Ex.
     * tryLockAccounts(acc1, acc2);
     * ...
     * unlockAccounts(acc1, acc2);
     *
     * @param from from.
     * @param to   to.
     * @return result.
     */
    private boolean tryLockAccounts(Account from, Account to) {
        boolean fromLock = false;
        boolean toLock = false;

        try {
            fromLock = from.lock.tryLock();
            toLock = to.lock.tryLock();
        } finally {
            if (!fromLock && toLock) {
                to.lock.unlock();
            }

            if (!toLock && fromLock) {
                from.lock.unlock();
            }
        }
        return fromLock && toLock;
    }

    /**
     * Arguments order when method calls should be the same as in {@see tryLockAccounts}.
     * Ex.
     * tryLockAccounts(acc1, acc2);
     * ...
     * unlockAccounts(acc1, acc2);
     *
     * @param from from.
     * @param to   to.
     */
    private void unlockAccounts(Account from, Account to) {
        from.lock.unlock();
        to.lock.unlock();
    }
}
