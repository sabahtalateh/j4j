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

    /**
     * @return hash code.
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    /**
     * @param o object.
     * @return true if equals, false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (children != user.children) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        return birthday != null ? birthday.equals(user.birthday) : user.birthday == null;
    }
}
