package com.sabahtalateh.j4j.oop.bank;

import com.sabahtalateh.j4j.oop.bank.time.Hour;
import com.sabahtalateh.j4j.oop.bank.time.HourPeriod;
import com.sabahtalateh.j4j.oop.bank.time.Minute;
import com.sabahtalateh.j4j.oop.bank.time.Time;

import java.util.*;

/**
 * BankDay.
 */
public class BankDay {

    static final Time BANK_DAY_START = new Time(8, 0);
    static final Time BANK_DAY_END = new Time(19, 59);

    private final Map<Hour, Integer> bankLoadingStatistic = new HashMap<>();

    private Time currentDayTime;

    private boolean dayStarted = false;

    private boolean dayCompleted = false;

    /**
     * Clients that are in bank now, with time when they were came in.
     */
    private final Map<Client, Time> clientsInBank = new HashMap<>();

    /**
     * Default constructor.
     */
    public BankDay() {
        for (int i = BANK_DAY_START.getHour().getValue(); i <= BANK_DAY_END.getHour().getValue(); i++) {
            bankLoadingStatistic.put(new Hour(i), 0);
        }
    }

    /**
     * Start bank day.
     */
    public void startDay() {
        this.currentDayTime = BANK_DAY_START;
        this.dayStarted = true;
    }

    /**
     * Complete bank day.
     */
    public void completeDay() {
        this.currentDayTime = BANK_DAY_END;

        Set<Client> clients = new HashSet<>(this.clientsInBank.keySet());
        for (Client c : clients) {
            this.clientLeft(c);
        }
        this.dayCompleted = true;
    }

    /**
     * @param client who came.
     * @throws BankIsNotWorkingException if bank is not working.
     */
    public void clientCame(Client client) throws BankIsNotWorkingException {
        if (!isBankWorking(this.currentDayTime)) {
            throw new BankIsNotWorkingException("Bank is not working now");
        }
        clientsInBank.put(client, this.currentDayTime);
    }

    /**
     * @param client who left.
     */
    public void clientLeft(Client client) {
        Time clientCameTime = clientsInBank.get(client);
        for (int i = clientCameTime.getHour().getValue(); i <= this.currentDayTime.getHour().getValue(); i++) {
            Hour hour = new Hour(i);
            bankLoadingStatistic.put(hour, bankLoadingStatistic.get(hour) + 1);
        }
        clientsInBank.remove(client);
    }

    /**
     * @return calculate time periods with highest loading.
     * @throws CanNotCalculateException if can not calculate.
     */
    public List<HourPeriod> calculateHighestLoadingPeriods() throws CanNotCalculateException {

        if (!this.dayCompleted) {
            throw new CanNotCalculateException("Can not calculate statistic of not finished day.");
        }

        ArrayList<HourPeriod> periods = new ArrayList<>();

        // Calculate maximum loading.
        Optional<Integer> maxLoading = this.bankLoadingStatistic.values().stream().max(Integer::compareTo);

        // Group hours by maximum loading.
        Map<Integer, List<Hour>> hoursByLoading = new HashMap<>();
        for (Map.Entry<Hour, Integer> e : this.bankLoadingStatistic.entrySet()) {
            hoursByLoading.putIfAbsent(e.getValue(), new ArrayList<>());
            hoursByLoading.get(e.getValue()).add(e.getKey());
        }

        // If there is no maximum loading at current day than return
        if (!maxLoading.isPresent()) {
            return periods;
        }

        // Sort maximum loading hours.
        List<Hour> maxLoadingHours = hoursByLoading.get(maxLoading.get());
        maxLoadingHours.sort(Hour::compareTo);

        // Perform periods.
        HourPeriod hourPeriod = new HourPeriod(maxLoadingHours.get(0), new Hour(maxLoadingHours.get(0).getValue() + 1));
        for (int i = 1; i < maxLoadingHours.size(); i++) {
            Hour hour = maxLoadingHours.get(i);
            if (hour.getValue() == hourPeriod.getEnd().getValue()) {
                hourPeriod = new HourPeriod(hourPeriod.getStart(), new Hour(hour.getValue() + 1));
            } else {
                periods.add(hourPeriod);
                hourPeriod = new HourPeriod(hour, new Hour(hour.getValue() + 1));
            }
        }

        periods.add(hourPeriod);

        return periods;
    }

    /**
     * Set current time.
     *
     * @param time to set.
     * @throws WrongTimeException if time is wrong.
     */
    public void setTime(Time time) throws WrongTimeException {
        if (time.compareTo(this.currentDayTime) >= 0) {
            throw new WrongTimeException("Time to set should be in future, current time is " + this.currentDayTime);
        }

        this.currentDayTime = time;
    }

    /**
     * Set current time.
     *
     * @param hours   to set.
     * @param minutes to set.
     * @throws WrongTimeException if time is wrong.
     */
    public void setTime(int hours, int minutes) throws WrongTimeException {
        this.setTime(new Time(new Hour(hours), new Minute(minutes)));
    }

    /**
     * @return boolean.
     */
    public boolean completed() {
        return this.dayCompleted;
    }

    /**
     * @return get statistic.
     */
    public Map<Hour, Integer> getBankLoadingStatistic() {
        return this.bankLoadingStatistic;
    }

    /**
     * @param time to check.
     * @return result.
     */
    private boolean isBankWorking(Time time) {
        return !this.dayCompleted && this.dayStarted && (time.getHour().getValue() >= 8 && time.getHour().getValue() <= 20);
    }
}
