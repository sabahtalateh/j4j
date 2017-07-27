package com.sabahtalateh.j4j.collections_advanced.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * UserStoreTest.
 */
public class UserStoreTest {
    @Test
    public void whenUserAddedItCanBeRetrieved() throws Exception {
        UserStore userStore = new UserStore();
        userStore.add(new User() {{
            setName("Ivane");
            setId("1");
        }});

        userStore.add(new User() {{
            setName("Nikolay");
            setId("2");
        }});

        userStore.add(new User() {{
            setName("Vitalik");
            setId("3");
        }});

        assertThat(userStore.findById("2").getName(), is("Nikolay"));
        assertThat(userStore.findById("3").getName(), is("Vitalik"));
    }

    @Test
    public void whenUserDeletesItIsNotInStore() throws Exception {
        UserStore userStore = new UserStore();

        User ivan = new User() {{
            setName("Ivan");
            setId("1");
        }};
        User marya = new User() {{
            setName("Marya");
            setId("2");
        }};

        userStore.add(ivan);
        userStore.add(marya);

        userStore.delete(ivan);

        assertThat(userStore.findById("1"), nullValue());
    }

    @Test
    public void whenUserUpdatesItHasNewName() throws Exception {
        UserStore userStore = new UserStore();

        User ivan = new User() {{
            setName("Ivan");
            setId("1");
        }};

        User marya = new User() {{
            setName("Marya");
            setId("2");
        }};

        userStore.add(ivan);
        userStore.add(marya);

        assertThat(userStore.findById("1").getName(), is("Ivan"));

        ivan.setName("Vitalik");
        userStore.update(ivan);

        assertThat(userStore.findById("1").getName(), is("Vitalik"));
    }
}