package com.sabahtalateh.j4j.collections_lite.sorting;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * SortUserTest.
 */
public class SortUserTest {
    @Test
    public void whenEmptyListPassEmptySetReturns() throws Exception {
        SortUser sortUser = new SortUser();
        assertThat(sortUser.sort(new ArrayList<>()), is(new HashSet<>()));
    }

    @Test
    public void whenSortUsersItSortedProperly() throws Exception {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        users.add(new User("Elena", 28));
        users.add(new User("Ivan", 45));
        users.add(new User("Igor", 22));
        users.add(new User("Ivan", 45));

        Set<User> expected = new TreeSet<>();
        expected.add(new User("Igor", 22));
        expected.add(new User("Elena", 28));
        expected.add(new User("Ivan", 45));

        assertThat(sortUser.sort(users), is(expected));
    }
}