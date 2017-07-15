package com.sabahtalateh.j4j.collections_lite.converter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * UserConverterTest.
 */
public class UserConverterTest {
    @Test
    public void convert() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User(0, "Ivan", "St. Petersburg"));
        users.add(new User(1, "Georgi", "Moscow"));
        users.add(new User(2, "Jack", "New York"));

        Map<Integer, User> expected = new HashMap<>();
        expected.put(0, new User(0, "Ivan", "St. Petersburg"));
        expected.put(1, new User(1, "Georgi", "Moscow"));
        expected.put(2, new User(2, "Jack", "New York"));

        assertThat(new UserConverter().convert(users), is(expected));
    }
}
