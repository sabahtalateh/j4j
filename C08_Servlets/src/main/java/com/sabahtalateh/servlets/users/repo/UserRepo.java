package com.sabahtalateh.servlets.users.repo;

import com.sabahtalateh.servlets.users.model.User;
import com.sabahtalateh.servlets.users.user_store.UserStore;

import javax.xml.bind.ValidationException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * UserRepo.
 */
public class UserRepo {

    private static AtomicInteger USER_ID = new AtomicInteger(0);

    private static UserRepo instance = null;

    private UserStore userStore = UserStore.getInstance();

    /**
     * Prevent from creating.
     */
    private UserRepo() {
    }

    /**
     * @return user repo.
     */
    synchronized public static UserRepo getInstance() {
        if (instance == null) {
            instance = new UserRepo();
        }
        return instance;
    }

    /**
     * @param user user.
     * @return result.
     * @throws ValidationException exception.
     */
    public boolean addUser(User user) throws ValidationException {

        if (null == user.getName()
                || null == user.getEmail()
                || null == user.getLogin()) {
            throw new ValidationException("Please check if all the required field are filled.");
        }

        User userToInsert = new User(USER_ID.getAndIncrement(), user.getName(), user.getLogin(), user.getEmail(), user.getDateTime());

        return userStore.add(userToInsert);
    }
}
