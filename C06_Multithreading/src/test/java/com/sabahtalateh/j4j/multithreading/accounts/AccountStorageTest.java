package com.sabahtalateh.j4j.multithreading.accounts;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * AccountStorageTest.
 */
public class AccountStorageTest {
    @Test
    public void whenAccountAddedItCanBeFound() throws Exception {
        AccountStorage accountStorage = new AccountStorage();
        Account acc1 = new Account("1", "Ivan", 100L);
        accountStorage.add(acc1);
        Account acc1FromStorage = accountStorage.get("1");
        assertThat(acc1FromStorage.getId(), is("1"));
        assertThat(acc1FromStorage.getOwnerName(), is("Ivan"));
        assertThat(acc1FromStorage.getAmount(), is(100L));

        Account acc2 = new Account("2", "Petr", 200L);
        accountStorage.add(acc2);
        Account acc2FromStorage = accountStorage.get("2");
        assertThat(acc2FromStorage.getId(), is("2"));
        assertThat(acc2FromStorage.getOwnerName(), is("Petr"));
        assertThat(acc2FromStorage.getAmount(), is(200L));
    }

    @Test
    public void whenUpdateAnAccountThenCanRetrieveNewData() throws Exception {
        AccountStorage accountStorage = new AccountStorage();
        Account acc1 = new Account("1", "Ivan", 10L);
        accountStorage.add(acc1);
        assertThat(accountStorage.update(new Account("1", "Nick", 20L)), is(true));
        assertThat(accountStorage.get("1").getOwnerName(), is("Nick"));
        assertThat(accountStorage.get("1").getAmount(), is(20L));
    }

    @Test
    public void canNotUpdateNonExistingAccount() throws Exception {
        AccountStorage accountStorage = new AccountStorage();
        assertThat(accountStorage.update(new Account("1", "Nick", 20L)), is(false));
    }

    @Test
    public void whenAccountDeletedThenItCanNotBeFound() throws Exception {
        AccountStorage accountStorage = new AccountStorage();
        Account acc1 = new Account("1", "Ivan", 10L);
        accountStorage.add(acc1);

        assertThat(accountStorage.get("1"), notNullValue());
        assertThat(accountStorage.delete(acc1), is(true));
        assertThat(accountStorage.get("1"), nullValue());
    }

    @Test
    public void canNotDeleteNonExistingAccount() throws Exception {
        AccountStorage accountStorage = new AccountStorage();
        assertThat(accountStorage.delete(new Account("1", "1", 1L)), is(false));
    }

    @Test
    public void canTransferIfMoneyIsEnough() throws Exception {
        AccountStorage accountStorage = new AccountStorage();
        Account acc1 = new Account("1", "Ivan", 10L);
        Account acc2 = new Account("2", "Nick", 20L);
        accountStorage.add(acc1);
        accountStorage.add(acc2);

        assertThat(accountStorage.transfer("1", "2", 5), is(true));
        assertThat(accountStorage.get("1").getAmount(), is(5L));
        assertThat(accountStorage.get("2").getAmount(), is(25L));

        assertThat(accountStorage.transfer("1", "2", 10), is(false));
        assertThat(accountStorage.get("1").getAmount(), is(5L));
        assertThat(accountStorage.get("2").getAmount(), is(25L));
    }

    @Test
    public void canNotTransferMoneyToNonExistingAccount() throws Exception {
        AccountStorage accountStorage = new AccountStorage();
        Account acc1 = new Account("1", "Ivan", 10L);
        accountStorage.add(acc1);

        assertThat(accountStorage.transfer("1", "2", 5), is(false));
        assertThat(accountStorage.get("1").getAmount(), is(10L));
    }
}