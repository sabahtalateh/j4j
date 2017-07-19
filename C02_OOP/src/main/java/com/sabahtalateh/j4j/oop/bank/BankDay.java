package com.sabahtalateh.j4j.oop.bank;

import com.sabahtalateh.j4j.oop.bank.time.Hour;
import com.sabahtalateh.j4j.oop.bank.time.Minute;
import com.sabahtalateh.j4j.oop.bank.time.Time;

import java.util.*;

/**
 * BankDay.
 */
public class BankDay {

    public static final Time BANK_DAY_START = new Time(8, 0);
    public static final Time BANK_DAY_END = new Time(19, 59);

    private Time currentDayTime;

    private boolean dayStarted = false;

    private boolean dayCompleted = false;

    private List<ClientTimePeriod> clientTimePeriods = new ArrayList<>();

    /**
     * Clients that are in bank now, with time when they were came in.
     */
    private final Map<Client, Time> clientsInBank = new HashMap<>();

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
        Time cameTime = clientsInBank.get(client);
        this.clientTimePeriods.add(new ClientTimePeriod(client, cameTime, this.currentDayTime));
        clientsInBank.remove(client);
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
     * @param time to check.
     * @return result.
     */
    private boolean isBankWorking(Time time) {
        return !this.dayCompleted && this.dayStarted && (time.getHour().getValue() >= 8 && time.getHour().getValue() <= 20);
    }

    /**
     * @return client time periods.
     */
    public List<ClientTimePeriod> getClientTimePeriods() {
        return this.clientTimePeriods;
    }
}
