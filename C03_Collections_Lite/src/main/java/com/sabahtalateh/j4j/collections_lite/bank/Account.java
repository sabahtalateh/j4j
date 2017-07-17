package com.sabahtalateh.j4j.collections_lite.bank;

/**
 * Account.
 */
public class Account {
    private int value;

    private String requisites;

    /**
     * @param value      value.
     * @param requisites account requisites.
     */
    public Account(String requisites, int value) {
        this.requisites = requisites;
        this.value = value;
    }

    /**
     * @return value.
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value value.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return requisites.
     */
    public String getRequisites() {
        return requisites;
    }

    /**
     * @param o to check.
     * @return result.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        return requisites != null ? requisites.equals(account.requisites) : account.requisites == null;
    }

    /**
     * @return result.
     */
    @Override
    public int hashCode() {
        return requisites != null ? requisites.hashCode() : 0;
    }
}
