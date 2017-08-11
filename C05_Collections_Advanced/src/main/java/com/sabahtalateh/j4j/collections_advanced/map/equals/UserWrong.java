package com.sabahtalateh.j4j.collections_advanced.map.equals;

/**
 * UserWrong.
 */
public class UserWrong {

    private String name;

    private int age;

    /**
     * @param name name.
     * @param age  age.
     */
    public UserWrong(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * @param o object.
     * @return equals.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        // Wrong comparison, should user o.getClass instead of instanceof.
        if (o == null || !(o instanceof UserWrong)) {
            return false;
        }

        UserWrong userWrong = (UserWrong) o;

        if (this.age != userWrong.age) {
            return false;
        }

        return this.name != null ? this.name.equals(userWrong.name) : userWrong.name == null;
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
