package com.sabahtalateh.j4j.oop.bank;

//import com.sabahtalateh.j4j.oop.bank.time.Hour;
//import com.sabahtalateh.j4j.oop.bank.time.HourPeriod;
import com.sabahtalateh.j4j.oop.bank.time.Time;
import com.sabahtalateh.j4j.oop.bank.time.TimePeriod;
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

        List<TimePeriod> expected = new ArrayList<TimePeriod>() {{
            add(new TimePeriod(BankDay.BANK_DAY_START, BankDay.BANK_DAY_END));
        }};

        assertThat(bank.calculateHighestLoadingPeriods(), is(expected));
    }

    @Test
    public void testStatisticsCalculation() throws Exception {
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

        List<TimePeriod> expected = new ArrayList<TimePeriod>() {{
            add(new TimePeriod(new Time(13, 52), new Time(13, 53)));
            add(new TimePeriod(new Time(14, 52), new Time(15, 20)));
        }};

        assertThat(bank.calculateHighestLoadingPeriods(), is(expected));
    }
}