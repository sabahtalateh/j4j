package com.sabahtalateh.j4j.collections_lite.bank;

/**
 * User.
 */
public class User {
    private String name;
    private String passport;

    /**
     * @param name     users name.
     * @param passport users passport.
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * @param o to check.
     * @return result.
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

        return passport != null ? passport.equals(user.passport) : user.passport == null;
    }

    /**
     * @return hash code.
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        return result;
    }
}
