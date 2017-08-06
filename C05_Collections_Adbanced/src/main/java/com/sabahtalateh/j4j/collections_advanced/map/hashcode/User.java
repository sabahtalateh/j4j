package com.sabahtalateh.j4j.collections_advanced.map.hashcode;

/**
 * UserWithWrongEquals.
 */
public class User {

    private String name;

    private int age;

    /**
     * @param name name.
     * @param age  age.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * @param o object.
     * @return equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        // Use correct class comparison.
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (age != user.age) {
            return false;
        }

        return name != null ? name.equals(user.name) : user.name == null;
    }

    /**
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }
}
