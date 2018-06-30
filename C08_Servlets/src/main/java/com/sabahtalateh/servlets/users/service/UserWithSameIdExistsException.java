package com.sabahtalateh.servlets.users.service;

public class UserWithSameIdExistsException extends Exception {
    public UserWithSameIdExistsException() {
    }

    public UserWithSameIdExistsException(String message) {
        super(message);
    }
}
