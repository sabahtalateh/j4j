package com.sabahtalateh.j4j.oop.bank;

import com.sabahtalateh.j4j.oop.bank.time.Hour;
import com.sabahtalateh.j4j.oop.bank.time.HourPeriod;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * BankTest.
 */
public class BankTest {
    @Test
    public void bankHasWholeDayStatisticsWhenNoClients() throws Exception {
        Bank bank = new Bank();
        bank.startDay();
        bank.completeDay();
        bank.startDay();
        bank.completeDay();

        List<HourPeriod> expected = new ArrayList<>();
        expected.add(new HourPeriod(new Hour(8), new Hour(20)));

        assertThat(bank.calculateHighestLoadingPeriods(), is(expected));
    }

    @Test
    public void testStatisticsCalculation() throws Exception {
        Bank bank = new Bank();
        Client ivan = new Client("Ivan");
        Client igor = new Client("Igor");
        Client masha = new Client("Masha");
        Client lena = new Client("Lena");

        bank.startDay();
        bank.clientCame(ivan);
        bank.setTime(8, 30);
        bank.clientLeft(ivan);

        bank.setTime(14, 10);
        bank.clientCame(igor);
        bank.setTime(16, 30);
        bank.clientLeft(igor);

        bank.completeDay();

        bank.startDay();
        bank.clientCame(lena);
        bank.clientCame(masha);
        bank.clientCame(ivan);
        bank.setTime(8, 20);
        bank.clientLeft(ivan);

        bank.setTime(14, 10);
        bank.clientCame(igor);
        bank.setTime(16, 30);
        bank.clientLeft(igor);

        bank.completeDay();

        List<HourPeriod> expected = new ArrayList<>();
        expected.add(new HourPeriod(new Hour(8), new Hour(9)));
        expected.add(new HourPeriod(new Hour(14), new Hour(17)));

        assertThat(bank.calculateHighestLoadingPeriods(), is(expected));
    }
}