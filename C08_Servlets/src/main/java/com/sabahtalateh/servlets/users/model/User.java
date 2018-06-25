package com.sabahtalateh.servlets.users.model;

import java.time.LocalDateTime;

/**
 * User.
 */
public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private LocalDateTime dateTime;

    /**
     * @param id       id.
     * @param name     name.
     * @param login    login.
     * @param email    email.
     * @param dateTime date time.
     */
    public User(int id, String name, String login, String email, LocalDateTime dateTime) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.dateTime = dateTime;
    }

    /**
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return date time.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
