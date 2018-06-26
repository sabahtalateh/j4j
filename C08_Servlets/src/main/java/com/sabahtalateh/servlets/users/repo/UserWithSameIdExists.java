package com.sabahtalateh.servlets.users.repo;

public class UserWithSameIdExists extends Exception {

    /**
     * Constructor.
     */
    public UserWithSameIdExists() {
    }

    /**
     * @param message message.
     */
    public UserWithSameIdExists(String message) {
        super(message);
    }
}
