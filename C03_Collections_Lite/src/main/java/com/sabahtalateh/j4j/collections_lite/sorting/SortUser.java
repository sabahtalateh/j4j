package com.sabahtalateh.j4j.collections_lite.sorting;

import java.util.*;

/**
 * SortUser.
 */
public class SortUser {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("Ivan", 14));
        users.add(new User("Ivan", 120));
        users.add(new User("Ivan", 13));
        users.add(new User("Ivan", 13));

        System.out.println(new SortUser().sort(users));
    }

    /**
     * @param users to sort.
     * @return sorted.
     */
    Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }
}
