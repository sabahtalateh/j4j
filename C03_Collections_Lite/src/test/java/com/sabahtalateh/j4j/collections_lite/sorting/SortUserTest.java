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

    @Test
    public void sortByNameLength() throws Exception {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        users.add(new User("Vladimir", 67));
        users.add(new User("Ivan", 32));
        users.add(new User("Konstantin", 19));
        users.add(new User("Elena", 45));

        List<User> expected = new ArrayList<>();
        expected.add(0, new User("Ivan", 32));
        expected.add(1, new User("Elena", 45));
        expected.add(2, new User("Vladimir", 67));
        expected.add(3, new User("Konstantin", 19));

        assertThat(sortUser.sortByNameLength(users), is(expected));
    }

    @Test
    public void sortByAllFields() throws Exception {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        users.add(new User("Ivan", 23));
        users.add(new User("Konstantin", 78));
        users.add(new User("Elena", 34));
        users.add(new User("Ivan", 36));

        List<User> expected = new ArrayList<>();
        expected.add(0, new User("Elena", 34));
        expected.add(1, new User("Ivan", 23));
        expected.add(2, new User("Ivan", 36));
        expected.add(3, new User("Konstantin", 78));

        assertThat(sortUser.sortByAllFields(users), is(expected));
    }
}