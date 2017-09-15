package com.sabahtalateh.j4j.multithreading.accounts;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;

/**
 * AccountStorage.
 */
@ThreadSafe
public class AccountStorage {
//    private final ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();
    private final Map<String, Account> accounts = new HashMap<>();

    /**
     * @param account account.
     * @return is added.
     */
    public boolean add(Account account) {
        return accounts.putIfAbsent(account.getId(), account) != account;
    }

    /**
     * @param id id.
     * @return optional account.
     */
    public Account get(String id) {
        return accounts.get(id);
    }
}
