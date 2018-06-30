package com.sabahtalateh.servlets.users.store;

import com.sabahtalateh.servlets.users.model.User;

import javax.jws.soap.SOAPBinding;
import java.util.Collection;
import java.util.Optional;

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
     * @param user user to add.
     * @return added user.
     */
    User replace(final User user);

    /**
     * @param user user.
     * @return user.
     */
    User delete(final User user);

    /**
     * @param id id.
     * @return result.
     */
    boolean contains(Long id);

    /**
     * @param id id.
     * @return user.
     */
    Optional<User> findById(Long id);
}
