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
    Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }
}
