package com.sabahtalateh.j4j.multithreading.accounts;

import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AccountStorage.
 */
@ThreadSafe
public class AccountStorage {
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();

    /**
     * @param account account.
     * @return is added.
     */
    public boolean add(Account account) {
        boolean added = false;
        if (!accounts.containsKey(account.getId())) {
            accounts.put(account.getId(), account);
            added = true;
        }
        return added;
    }

    /**
     * @param newAccount new account.
     * @return is updated.
     */
    public boolean update(Account newAccount) {
        boolean updated = false;
        Account oldAccount = accounts.get(newAccount.getId());
        if (oldAccount != null) {
            try {
                newAccount.lock.lock();
                oldAccount.lock.lock();
                accounts.put(newAccount.getId(), newAccount);
                updated = true;
            } finally {
                oldAccount.lock.unlock();
                newAccount.lock.unlock();
            }
        }
        return updated;
    }

    /**
     * @param account account.
     * @return is deleted.
     */
    public boolean delete(Account account) {
        boolean deleted = false;
        Account deletingAccount = accounts.get(account.getId());
        if (deletingAccount != null) {
            try {
                deletingAccount.lock.lock();
                accounts.remove(account.getId());
                deleted = true;
            } finally {
                deletingAccount.lock.unlock();
            }

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
    public Account get(String id) {
        return accounts.get(id);
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
