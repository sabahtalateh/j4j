package com.sabahtalateh.servlets.users.user_store;

import com.sabahtalateh.servlets.users.model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * UserStore.
 */
public class UserStore {

    private static UserStore instance = null;

    private static final List<User> USERS = new CopyOnWriteArrayList<>();

    /**
     * Prevent from calling from outside of the package.
     */
    private UserStore() {
    }

    /**
     * @return user store.
     */
    public static UserStore getInstance() {
        if (instance == null) {
            instance = new UserStore();
        }
        return instance;
    }

    /**
     * @param user user.
     * @return result.
     */
    public boolean add(User user) {
        return USERS.add(user);
    }

    /**
     * @return users list.
     */
    public List<User> asList() {
        return USERS;
    }
}
