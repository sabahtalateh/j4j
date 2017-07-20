package com.sabahtalateh.j4j.oop.bank;

/**
 * Run.
 */
public class Run {
    /**
     * @param args a
     * @throws BankIsNotWorkingException a
     * @throws WrongTimeException        a
     * @throws CanNotCalculateException  a
     * @throws CanNotStartDayException   a
     */
    public static void main(String[] args) throws BankIsNotWorkingException, WrongTimeException, CanNotCalculateException, CanNotStartDayException {

        Bank bank = new Bank();

        Client c1 = new Client("Vova");
        Client c2 = new Client("Misha");
        Client c3 = new Client("Masha");
        Client c4 = new Client("Borya");

        bank.startDay();
        bank.clientCame(c3);
        bank.setTime(13, 46);
        bank.clientCame(c1);
        bank.completeDay();

        bank.startDay();
        bank.clientCame(c2);
        bank.setTime(13, 52);
        bank.clientCame(c4);
        bank.setTime(13, 53);
        bank.clientLeft(c4);

        bank.setTime(14, 52);
        bank.clientCame(c4);
        bank.setTime(15, 20);
        bank.clientLeft(c4);

        bank.completeDay();



        System.out.println(bank.calculateHighestLoadingPeriods());

//
//        bankDay.setTime(10, 15);
//        bankDay.clientCame(c2);
//        bankDay.clientCame(c3);
//
//        bankDay.setTime(12, 49);
//        bankDay.clientLeft(c1);
//        bankDay.setTime(16, 15);
//        bankDay.clientCame(c4);
//        bankDay.clientLeft(c2);
//
//        bankDay.setTime(19, 34);
////        bankDay.clientLeft(c3);
////        bankDay.clientLeft(c4);
//        bankDay.completeDay();
//
//        System.out.println(bankDay.calculateHighestLoadingPeriods());
    }
}
