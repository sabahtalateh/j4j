package com.sabahtalateh.j4j.oop.bank;

import com.sabahtalateh.j4j.oop.bank.time.Hour;
import com.sabahtalateh.j4j.oop.bank.time.HourPeriod;
import com.sabahtalateh.j4j.oop.bank.time.Time;

import java.util.*;

/**
 * BankDay.
 */
public class BankDay {

    private final Map<Hour, Integer> bankLoadingStatistic = new HashMap<>();

    /**
     * Clients that are in bank now, with time when they were came in.
     */
    private final Map<Client, Time> clientsInBank = new HashMap<>();

    /**
     * @param client who came.
     * @param time   when came.
     */
    public void clientCame(Client client, Time time) throws BankIsNotWorkingException {
        if (!isBankWorking(time)) {
            throw new BankIsNotWorkingException("Bank is not working at " + time);
        }
        clientsInBank.put(client, time);
    }

    /**
     * @param client who left.
     * @param time   when left.
     */
    public void clientLeft(Client client, Time time) {
        Time clientCameTime = clientsInBank.get(client);
        for (int i = clientCameTime.getHour().getValue(); i <= time.getHour().getValue(); i++) {
            Hour hour = new Hour(i);
            bankLoadingStatistic.putIfAbsent(hour, 0);
            bankLoadingStatistic.put(hour, bankLoadingStatistic.get(hour) + 1);
        }
        clientsInBank.remove(client);
    }

    public List<HourPeriod> calculateHighstLoadingPeriods() {
        ArrayList<HourPeriod> periods = new ArrayList<>();

        Optional<Integer> maxLoading = this.bankLoadingStatistic.values().stream().max(Integer::compareTo);

        Map<Integer, List<Hour>> hoursByLoading = new HashMap<>();

        for (Map.Entry<Hour, Integer> e: this.bankLoadingStatistic.entrySet()) {
            hoursByLoading.putIfAbsent(e.getValue(), new ArrayList<>());
            hoursByLoading.get(e.getValue()).add(e.getKey());
        }

        if (!maxLoading.isPresent()) {
            return periods;
        }

        List<Hour> maxLoadingHours = hoursByLoading.get(maxLoading.get());
        maxLoadingHours.sort(Hour::compareTo);


        // TODO допилить интервалы
        System.out.println(maxLoadingHours);

        return periods;
    }

    /**
     * @param time to check.
     * @return result.
     */
    private boolean isBankWorking(Time time) {
        return time.getHour().getValue() >= 8 && time.getHour().getValue() <= 20;
    }
}
