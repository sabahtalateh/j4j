package com.sabahtalateh.j4j.multithreading.accounts;

import java.util.*;

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
     * @param from   from.
     * @param to     to.
     * @param amount amount.
     * @return is transfered.
     */
    public boolean transfer(Account from, Account to, long amount) {
        boolean transfered = false;
        from = accounts.get(from.getId());
        to = accounts.get(to.getId());
        if (from != null && to != null && from.getAmount() >= amount) {
            from.subtractAmount(amount);
            to.addAmount(amount);
            transfered = true;
        }

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
}
