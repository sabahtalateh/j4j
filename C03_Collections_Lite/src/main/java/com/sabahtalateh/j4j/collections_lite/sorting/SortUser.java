package com.sabahtalateh.j4j.collections_lite.sorting;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * SortUser.
 */
public class SortUser {
    /**
     * @param users to sort.
     * @return sorted.
     */
    public Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }

    /**
     * @param users to sort.
     * @return sorted.
     */
    public List<User> sortByNameLength(List<User> users) {
        users.sort((user1, user2) -> ((Integer) user1.getName().length()).compareTo(user2.getName().length()));
        return users;
    }

    /**
     * @param users to sort.
     * @return sorted.
     */
    public List<User> sortByAllFields(List<User> users) {
        users.sort((o1, o2) -> {
            int nameComparing = o1.getName().compareTo(o2.getName());
            return nameComparing == 0 ? ((Integer) o1.getAge()).compareTo(o2.getAge()) : nameComparing;
        });
        return users;
    }
}
