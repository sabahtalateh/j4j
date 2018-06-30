package com.sabahtalateh.servlets.users.service;

public class UserDoesNotExistsException extends Exception {
    public UserDoesNotExistsException() {
    }

    public UserDoesNotExistsException(String message) {
        super(message);
    }
}
