package com.sabahtalateh.servlets.users.store;

import com.sabahtalateh.servlets.users.model.User;

import javax.jws.soap.SOAPBinding;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MemoryStore.
 */
public class MemoryStore implements UserStore {

    private static MemoryStore instance = null;

    private final Map<Long, User> users = new ConcurrentHashMap<>();

    /**
     * Prevent from calling from outside of the package.
     */
    private MemoryStore() {
    }

    /**
     * @return user store.
     */
    public static synchronized MemoryStore getInstance() {
        if (instance == null) {
            instance = new MemoryStore();
        }
        return instance;
    }

    @Override
    public User add(final User user) {
        users.putIfAbsent(user.getId(), user);
        return user;
    }

    @Override
    public User replace(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User delete(User user) {
        users.remove(user.getId());
        return user;
    }

    @Override
    public boolean contains(Long id) {
        return users.containsKey(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = users.get(id);
        if (null != user) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public Collection<User> asCollection() {
        return users.values();
    }
}
