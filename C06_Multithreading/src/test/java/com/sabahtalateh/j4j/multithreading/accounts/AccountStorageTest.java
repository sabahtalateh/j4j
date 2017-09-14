package com.sabahtalateh.j4j.multithreading.accounts;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * AccountStorageTest.
 */
public class AccountStorageTest {
    @Test
    public void whenAccountAddedThenItCanBeRetrieved() throws Exception {
        AccountStorage storage = new AccountStorage();
        Account ivan = new Account("1", "Ivan", 100);
        Account maxim = new Account("2", "Maxim", 200);
        assertThat(storage.add(ivan), is(true));
        assertThat(storage.add(maxim), is(true));
        assertThat(storage.get("1"), is(ivan));
        assertThat(storage.get("2"), is(maxim));
    }

    @Test
    public void nonExistingAccountsCanNotBeRetrieved() throws Exception {
        AccountStorage storage = new AccountStorage();
        Account ivan = new Account("1", "Ivan", 100);
        assertThat(storage.add(ivan), is(true));
        assertThat(storage.get("2"), nullValue());
    }

    @Test
    public void deletedAccountsCanNotBeRetrieved() throws Exception {
        AccountStorage storage = new AccountStorage();
        Account ivan = new Account("1", "Ivan", 100);
        assertThat(storage.add(ivan), is(true));
        assertThat(storage.get("1"), is(ivan));

        assertThat(storage.delete(ivan), is(true));
        assertThat(storage.get("1"), nullValue());
    }

    @Test
    public void whenUpdateAccountThenItUpdates() throws Exception {
        AccountStorage storage = new AccountStorage();
        Account ivan = new Account("1", "Ivan", 100);
        assertThat(storage.add(ivan), is(true));

        Account newIvan = new Account("1", "Ivan Ivanovich", 500);
        assertThat(storage.update(newIvan), is(true));
        assertThat(storage.get("1"), is(newIvan));

        Account notInStorage = new Account("100", "Petr", 400);
        assertThat(storage.update(notInStorage), is(false));
    }

    @Test
    public void canNotTransferMoneyIfMoneyIsNotEnough() throws Exception {
        AccountStorage storage = new AccountStorage();
        Account ivan = new Account("1", "Ivan", 100);
        Account maxim = new Account("2", "Maxim", 200);
        assertThat(storage.add(ivan), is(true));
        assertThat(storage.add(maxim), is(true));

        assertThat(ivan.getAmount(), is(100L));
        assertThat(maxim.getAmount(), is(200L));

        assertThat(storage.transfer("1", "2", 500), is(false));
        assertThat(storage.transfer("1", "2", 50), is(true));

        assertThat(ivan.getAmount(), is(50L));
        assertThat(maxim.getAmount(), is(250L));
    }
}