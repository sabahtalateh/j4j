package com.sabahtalateh.j4j.collections_advanced.map;

import java.util.Calendar;

/**
 * User.
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    /**
     * @param name     name.
     * @param children children count.
     * @param birthday birthday.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return children count.
     */
    public int getChildren() {
        return children;
    }

    /**
     * @return birthday.
     */
    public Calendar getBirthday() {
        return birthday;
    }
}
