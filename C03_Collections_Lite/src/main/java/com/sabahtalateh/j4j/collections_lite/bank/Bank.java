package com.sabahtalateh.j4j.collections_lite.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bank.
 */
public class Bank {

    private final Map<User, List<Account>> accounts = new HashMap<>();

    /**
     * @param user to add.
     * @throws CanNotAddUserException exception.
     */
    public void addUser(User user) throws CanNotAddUserException {
        if (this.accounts.containsKey(user)) {
            throw new CanNotAddUserException("Such user already exists.");
        }
        this.accounts.put(user, new ArrayList<>());
    }

    /**
     * @param user to delete.
     */
    public void deleteUser(User user) {
        this.accounts.remove(user);
    }

    /**
     * @param user    to assign account.
     * @param account to assign to user.
     * @throws CanNotAddAccountException exception.
     */
    public void linkAccountToUser(User user, Account account) throws CanNotAddAccountException {
        if (this.accounts.get(user).indexOf(account) != -1) {
            throw new CanNotAddAccountException("Such account is already linked to this user.");
        }
        this.accounts.get(user).add(account);
    }

    /**
     * @param user    to unlink.
     * @param account to unlink account from user.
     */
    public void unlinkAccountFromUser(User user, Account account) {
        this.accounts.get(user).remove(account);
    }

    /**
     * @param user user.
     * @return accounts.
     */
    public List<Account> getUserAccounts(User user) {
        return this.accounts.get(user);
    }

    /**
     * @param userFrom    user.
     * @param accountFrom account.
     * @param userTo      user.
     * @param accountTo   account
     * @param amount      amount.
     * @return result.
     */
    public boolean transferMoney(User userFrom, Account accountFrom, User userTo, Account accountTo, int amount) {
        int accountFromIdx = this.accounts.get(userFrom).indexOf(accountFrom);
        int accountToIdx = this.accounts.get(userTo).indexOf(accountTo);
        if (accountFromIdx == -1 || accountToIdx == -1
                || this.accounts.get(userFrom).get(accountFromIdx).getValue() < amount) {
            return false;
        }

        accountFrom = this.accounts.get(userFrom).get(accountFromIdx);
        accountTo = this.accounts.get(userTo).get(accountToIdx);
        accountFrom.setValue(accountFrom.getValue() - amount);
        accountTo.setValue(accountTo.getValue() + amount);

        return true;
    }
}
