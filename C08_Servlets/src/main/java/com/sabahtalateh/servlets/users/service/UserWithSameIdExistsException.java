package com.sabahtalateh.servlets.users.service;

/**
 * UserWithSameIdExistsException.
 */
public class UserWithSameIdExistsException extends Exception {
    /**
     * Constructor.
     */
    public UserWithSameIdExistsException() {
    }

    /**
     * @param message message.
     */
    public UserWithSameIdExistsException(String message) {
        super(message);
    }
}
