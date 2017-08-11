package com.sabahtalateh.j4j.collections_advanced.map.equals;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * UserEqualsTest.
 */
public class UserEqualsTest {
    @Test
    public void reflection() throws Exception {
        User user = new User("Ivan", 20);
        assertThat(user.equals(user), is(true));
    }

    @Test
    public void symmetryWithWrongEquals() throws Exception {
        User user1 = new User("Ivan", 20);
        User user2 = new User("Ivan", 20);

        assertThat(user1.equals(user2), is(true));
        assertThat(user2.equals(user1), is(true));

        User employee = new Employee("Ivan", 20, "Spaceship captain");

        assertThat(user1.equals(employee), is(false));
        assertThat(employee.equals(user1), is(false));
    }

    @Test
    public void ifEqualsIsNotCorrectThenTransitionIsViolated() throws Exception {
        EmployeeWrong ivanCaptain = new EmployeeWrong("Ivan", 20, "Spaceship captain");
        UserWrong ivan = new UserWrong("Ivan", 20);
        EmployeeWrong ivanSongWriter = new EmployeeWrong("Ivan", 20, "Song writer");

        assertThat(ivanCaptain.equals(ivan), is(true));
        assertThat(ivan.equals(ivanSongWriter), is(true));
        assertThat(ivanCaptain.equals(ivanSongWriter), is(false));
    }

    @Test
    public void ifEqualsIsCorrectThenTransitionWorksAsExpected() throws Exception {
        Employee ivanCaptain = new Employee("Ivan", 20, "Spaceship captain");
        User ivan = new User("Ivan", 20);
        Employee ivanSongWriter = new Employee("Ivan", 20, "Song writer");

        assertThat(ivanCaptain.equals(ivan), is(false));
        assertThat(ivan.equals(ivanSongWriter), is(false));
        assertThat(ivanCaptain.equals(ivanSongWriter), is(false));

        Employee ivanCaptain1 = new Employee("Ivan", 20, "Spaceship captain");
        Employee ivanCaptain2 = new Employee("Ivan", 20, "Spaceship captain");
        Employee ivanCaptain3 = new Employee("Ivan", 20, "Spaceship captain");

        assertThat(ivanCaptain1.equals(ivanCaptain2), is(true));
        assertThat(ivanCaptain2.equals(ivanCaptain3), is(true));
        assertThat(ivanCaptain1.equals(ivanCaptain3), is(true));
    }

    @Test
    public void notNull() throws Exception {
        User user = new User("Ivan", 20);
        assertThat(user.equals(null), is(false));
    }
}