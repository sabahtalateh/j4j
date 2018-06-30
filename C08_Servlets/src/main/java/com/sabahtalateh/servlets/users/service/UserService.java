package com.sabahtalateh.servlets.users.service;

import com.sabahtalateh.servlets.users.model.User;
import com.sabahtalateh.servlets.users.store.IdSequence;
import com.sabahtalateh.servlets.users.store.MemoryStore;
import com.sabahtalateh.servlets.users.store.UserStore;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public class UserService {

    private static UserService instance = null;

    private final UserStore userStore = MemoryStore.getInstance();

    private final IdSequence idSequence = new IdSequence();

    /**
     * Prevent from calling from outside of the package.
     */
    private UserService() {
    }

    /**
     * @return user service.
     */
    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    /**
     * @return users.
     */
    public Collection<User> getUsersCollection() {
        return userStore.asCollection();
    }

    /**
     * @param name        name.
     * @param login       login.
     * @param email       email.
     * @param dateCreated created date.
     * @return user.
     */
    public User addUser(String name, String login, String email, LocalDateTime dateCreated) {
        User user = new User(idSequence.next(), name, login, email, dateCreated);
        userStore.add(user);
        return user;
    }

    /**
     * @param id          id.
     * @param name        name.
     * @param login       login.
     * @param email       email.
     * @param dateCreated created date.
     * @return user.
     * @throws UserDoesNotExistsException exception.
     */
    public User replaceUser(Long id, String name, String login, String email, LocalDateTime dateCreated) throws UserDoesNotExistsException {
        User insertedUser;
        synchronized (userStore) {
            if (!userStore.contains(id)) {
                throw new UserDoesNotExistsException(String.format("User with id %s does not exists.", id));
            }
            insertedUser = userStore.replace(new User(id, name, login, email, dateCreated));
        }

        return insertedUser;
    }

    /**
     * @param id user id to delete.
     * @return deleted user.
     * @throws UserDoesNotExistsException exception.
     */
    public User deleteUser(Long id) throws UserDoesNotExistsException {
        synchronized (userStore) {
            Optional<User> userToDelete = userStore.findById(id);
            if (!userToDelete.isPresent()) {
                throw new UserDoesNotExistsException(String.format("User with id %s does not exists.", id));
            }
            return userStore.delete(userToDelete.get());
        }
    }

    /**
     * @param id id.
     * @return user.
     */
    public Optional<User> findUserById(Long id) {
        return userStore.findById(id);
    }
}
