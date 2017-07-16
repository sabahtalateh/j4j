package com.sabahtalateh.j4j.oop.bank;

import com.sabahtalateh.j4j.oop.bank.time.Time;

/**
 * Bank.
 */
public class Bank {
    public static void main(String[] args) throws BankIsNotWorkingException {


        BankDay bankDay = new BankDay();

        Client c1 = new Client("Vitalik");
        bankDay.clientCame(c1, new Time(10, 55));
        bankDay.clientLeft(c1, new Time(12, 30));


        Client c2 = new Client("Igor");
        bankDay.clientCame(c2, new Time(11, 20));
        bankDay.clientLeft(c2, new Time(16, 0));

        Client c3 = new Client("Igor");
        bankDay.clientCame(c3, new Time(17, 20));
        bankDay.clientLeft(c3, new Time(17, 30));

        Client c4 = new Client("Igor");
        bankDay.clientCame(c4, new Time(17, 30));
        bankDay.clientLeft(c4, new Time(17, 40));

        bankDay.calculateHighstLoadingPeriods();
    }
}
