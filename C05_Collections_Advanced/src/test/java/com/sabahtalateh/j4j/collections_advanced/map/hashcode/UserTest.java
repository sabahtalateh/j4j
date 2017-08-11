package com.sabahtalateh.j4j.collections_advanced.map.hashcode;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * UserTest.
 */
public class UserTest {
    @Test
    public void hashCodeOnSameObjectsIsTheSame() throws Exception {
        User user1 = new User("Ivan", 40);
        User user2 = new User("Ivan", 40);

        assertThat(user1.hashCode(), is(user2.hashCode()));
    }

    @Test
    public void hashCodeObChildrenAreEquals() throws Exception {
        Employee employee1 = new Employee("Ivan", 20, "Worker");
        Employee employee2 = new Employee("Ivan", 20, "Worker");

        assertThat(employee1.hashCode(), is(employee2.hashCode()));
    }
}
