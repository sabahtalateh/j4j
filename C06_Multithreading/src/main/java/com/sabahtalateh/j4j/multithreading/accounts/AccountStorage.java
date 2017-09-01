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
    public boolean add(Account account) {
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
    public boolean update(Account account) {
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
    public boolean delete(Account account) {
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

        // Try lock should stand before the amount checking.
        if (from != null && to != null && this.tryLockAccounts(from, to) && from.getAmount() >= amount) {

            long checkBefore = from.getAmount() - to.getAmount();

            from.subtractAmount(amount);
            to.addAmount(amount);
            transfered = true;

            long checkAfter = from.getAmount() - to.getAmount() + (2 * amount);

            if (checkBefore != checkAfter) {
                from.addAmount(amount);
                to.subtractAmount(amount);
                transfered = false;
            }

            this.unlockAccounts(from, to);
        }

//        if (from != null && to != null && from.getAmount() >= amount) {
//            Account first;
//            Account second;
//            if (from.hashCode() < to.hashCode()) {
//                first = from;
//                second = to;
//            } else {
//                first = to;
//                second = from;
//            }
//
//            synchronized (first) {
//                synchronized (second) {
//                    from.subtractAmount(amount);
//                    to.addAmount(amount);
//                    transfered = true;
//                }
//            }
//        }

        return transfered;
    }

    /**
     * @param id id.
     * @return optional account.
     */
    public Optional<Account> findByAccountId(String id) {
        Account account = accounts.get(id);
        return account == null ? Optional.empty() : Optional.of(account);
    }

    /**
     * True if both lock was acquired.
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
     * @param from from.
     * @param to   to.
     */
    private void unlockAccounts(Account from, Account to) {
        to.lock.unlock();
        from.lock.unlock();
    }
}
