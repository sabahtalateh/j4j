package com.sabahtalateh.servlets.users.service;

/**
 * UserDoesNotExistsException.
 */
public class UserDoesNotExistsException extends Exception {
    /**
     * Constructor.
     */
    public UserDoesNotExistsException() {
    }

    /**
     * @param message message.
     */
    public UserDoesNotExistsException(String message) {
        super(message);
    }
}
