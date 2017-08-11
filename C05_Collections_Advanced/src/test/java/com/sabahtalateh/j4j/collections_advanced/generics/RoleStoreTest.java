package com.sabahtalateh.j4j.collections_advanced.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * RoleStoreTest.
 */
public class RoleStoreTest {
    @Test
    public void whenAddRoleItPersistsInStore() throws Exception {
        RoleStore roleStore = new RoleStore();

        Role admin = new Role() {{
            setId("1");
            setName("Admin");
            grant("DELETE");
            grant("ADD");
            grant("READ");
        }};

        roleStore.add(admin);
        assertThat(roleStore.findById("1"), is(admin));
    }

    @Test
    public void whenRoleDeletesItIsNotInStoreAnymore() throws Exception {
        RoleStore roleStore = new RoleStore();

        Role admin = new Role() {{
            setId("1");
            setName("Admin");
            grant("DELETE");
            grant("ADD");
        }};

        Role user = new Role() {{
            setId("2");
            setName("User");
            grant("READ");
        }};

        roleStore.add(admin);
        roleStore.add(user);
        assertThat(roleStore.findById("2"), is(user));

        roleStore.delete(user);
        assertThat(roleStore.findById("2"), nullValue());
    }

    @Test
    public void whenRoleUpdatesItPersistInStore() throws Exception {
        RoleStore roleStore = new RoleStore();

        Role admin = new Role() {{
            setId("1");
            setName("Admin");
            grant("DELETE");
            grant("READ");
            grant("ADD");
        }};

        Role user = new Role() {{
            setId("2");
            setName("User");
            grant("READ");
        }};

        roleStore.add(admin);
        roleStore.add(user);

        assertThat(roleStore.findById("2").getName(), is("User"));

        user.setName("New Value");

        roleStore.update(user);

        assertThat(roleStore.findById("2").getName(), is("New Value"));
    }
}