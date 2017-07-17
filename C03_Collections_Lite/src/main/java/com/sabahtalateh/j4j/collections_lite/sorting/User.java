package com.sabahtalateh.j4j.collections_lite.sorting;

/**
 * User.
 */
public class User implements Comparable<User> {
    private String name;
    private int age;

    /**
     * @param name of user.
     * @param age  of user.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return age.
     */
    public int getAge() {
        return age;
    }

    /**
     * @param user to compare.
     * @return result.
     */
    @Override
    public int compareTo(User user) {
        return ((Integer) this.getAge()).compareTo(user.getAge());
    }

    @Override
    public String toString() {
        return "User{name='" + name + ", age=" + age + '}';
    }

    /**
     * @param o to compare.
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

        if (age != user.age) {
            return false;
        }
        return name != null ? name.equals(user.name) : user.name == null;
    }

    /**
     * @return hash code.
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }
}
