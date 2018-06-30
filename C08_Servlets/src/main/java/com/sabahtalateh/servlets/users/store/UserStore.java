package com.sabahtalateh.servlets.users.store;

import com.sabahtalateh.servlets.users.model.User;

import java.util.Collection;
import java.util.Optional;

/**
 * UserStore.
 */
public interface UserStore {
    /**
     * @return users.
     */
    Collection<User> asCollection();

    /**
     * @param user user to add.
     * @return added user.
     */
    User add(final User user);

    /**
     * @param user user to replace.
     * @return old user.
     */
    User replace(final User user);

    /**
     * @param user user to delete.
     * @return deleted user.
     */
    User delete(final User user);

    /**
     * @param id user id.
     * @return result.
     */
    boolean contains(Long id);

    /**
     * @param id user id.
     * @return user.
     */
    Optional<User> findById(Long id);
}
