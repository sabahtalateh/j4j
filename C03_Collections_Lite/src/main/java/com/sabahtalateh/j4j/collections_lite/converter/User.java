package com.sabahtalateh.j4j.collections_lite.converter;

/**
 * User.
 */
public class User {
    private final int id;
    private final String name;
    private final String city;

    /**
     * @param id   of user.
     * @param name of user.
     * @param city of user.
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
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
     * @return city.
     */
    public String getCity() {
        return city;
    }

    /**
     * {@inheritDoc}
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

        if (getId() != user.getId()) {
            return false;
        }

        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) {
            return false;
        }
        return getCity() != null ? getCity().equals(user.getCity()) : user.getCity() == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        return result;
    }
}
