package com.sabahtalateh.servlets.users.repo;

import com.sabahtalateh.servlets.users.model.User;
import com.sabahtalateh.servlets.users.user_store.UserStore;

import javax.xml.bind.ValidationException;

/**
 * UsersRepo.
 */
public class UsersRepo {
    private UserStore userStore = UserStore.getInstance();

    /**
     * @param user user.
     * @return result.
     * @throws ValidationException ecxeption.
     */
    public boolean addUser(User user) throws ValidationException {

        if (null == user.getName()
                || null == user.getEmail()
                || null == user.getLogin()) {
            throw new ValidationException("Please check if all the required field are filled.");
        }

        return userStore.add(user);
    }
}
