package com.sabahtalateh.j4j.collections_lite.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * BankTest.
 */
public class BankTest {
    @Test(expected = CanNotAddUserException.class)
    public void exceptionThrowingWhenTryingToAddExistingUser() throws Exception {
        Bank bank = new Bank();
        bank.addUser(new User("Ivan", "123123"));
        bank.addUser(new User("Ivan", "123123"));
    }

    @Test(expected = CanNotAddAccountException.class)
    public void canNotAddSameAccounts() throws Exception {
        Bank bank = new Bank();
        bank.addUser(new User("Ivan", "123123"));
        bank.linkAccountToUser(new User("Ivan", "123123"), new Account("777", 100));
        bank.linkAccountToUser(new User("Ivan", "123123"), new Account("777", 100));
    }

    @Test
    public void addedAccountsIsOwndeByUser() throws Exception {
        Bank bank = new Bank();
        bank.addUser(new User("Ivan", "123123"));
        bank.linkAccountToUser(new User("Ivan", "123123"), new Account("777", 100));
        List<Account> usersAccounts = new ArrayList<>();
        usersAccounts.add(new Account("777", 100));
        assertThat(bank.getUserAccounts(new User("Ivan", "123123")), is(usersAccounts));
    }

    @Test
    public void deletedAccountIsNoLongerOwndeByUser() throws Exception {
        Bank bank = new Bank();
        bank.addUser(new User("Ivan", "123123"));
        bank.linkAccountToUser(new User("Ivan", "123123"), new Account("777", 100));
        bank.unlinkAccountFromUser(new User("Ivan", "123123"), new Account("777", 100));
        assertThat(bank.getUserAccounts(new User("Ivan", "123123")), is(new ArrayList<>()));
    }

    @Test
    public void canNotTransferToUnexistingAccount() throws Exception {
        Bank bank = new Bank();
        User user = new User("Ivan", "123123");
        bank.addUser(user);
        bank.linkAccountToUser(user, new Account("777", 100));
        bank.linkAccountToUser(user, new Account("888", 100));

        boolean transferResult = bank.transferMoney(user, new Account("777", 100), user, new Account("999", 100), 20);
        assertThat(transferResult, is(false));
    }

    @Test
    public void canNotTransferFromUnexistingAccount() throws Exception {
        Bank bank = new Bank();
        User user = new User("Ivan", "123123");
        bank.addUser(user);
        bank.linkAccountToUser(user, new Account("777", 100));
        bank.linkAccountToUser(user, new Account("888", 100));

        boolean transferResult = bank.transferMoney(user, new Account("999", 100), user, new Account("777", 100), 100);
        assertThat(transferResult, is(false));
    }

    @Test
    public void canNotTransferMoreMoneyThanIsOnTheAccount() throws Exception {
        Bank bank = new Bank();
        User user = new User("Ivan", "123123");
        bank.addUser(user);

        Account account1 = new Account("777", 100);
        Account account2 = new Account("888", 100);

        bank.linkAccountToUser(user, account1);
        bank.linkAccountToUser(user, account2);

        boolean transferResult = bank.transferMoney(user, account1, user, account2, 200);
        assertThat(transferResult, is(false));
    }

    @Test
    public void whenTransferMoneyThanBalanceChanges() throws Exception {
        Bank bank = new Bank();
        User user1 = new User("Ivan", "123123");
        User user2 = new User("Ignat", "456456");
        bank.addUser(user1);
        bank.addUser(user2);

        Account account1 = new Account("777", 100);
        Account account2 = new Account("888", 100);

        bank.linkAccountToUser(user1, account1);
        bank.linkAccountToUser(user2, account2);

        boolean transferResult = bank.transferMoney(user1, account1, user2, account2, 50);
        assertThat(transferResult, is(true));
        assertThat(account1.getValue(), is(50));
        assertThat(account2.getValue(), is(150));
    }
}
