package com.sabahtalateh.j4j.multithreading.accounts;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
//import static org.hamcrest.core.IsNull.nullValue;
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
}