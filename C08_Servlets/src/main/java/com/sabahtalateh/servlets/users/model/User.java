package com.sabahtalateh.servlets.users.model;

import java.time.LocalDateTime;

/**
 * User.
 */
public class User {
    private Long id;
    private String name;
    private String login;
    private String email;
    private LocalDateTime dateTime;

    /**
     * @param name     name.
     * @param login    login.
     * @param email    email.
     * @param dateTime date time.
     */
    public User(String name, String login, String email, LocalDateTime dateTime) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.dateTime = dateTime;
    }

    /**
     * @param id       id.
     * @param name     name.
     * @param login    login.
     * @param email    email.
     * @param dateTime date time.
     */
    public User(Long id, String name, String login, String email, LocalDateTime dateTime) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.dateTime = dateTime;
    }

    /**
     * @return id.
     */
    public Long getId() {
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
