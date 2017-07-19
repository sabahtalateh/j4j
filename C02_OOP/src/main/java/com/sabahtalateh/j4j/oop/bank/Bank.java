package com.sabahtalateh.j4j.oop.bank;

import com.sabahtalateh.j4j.oop.bank.time.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Bank.
 */
public class Bank {

    private final List<BankDay> bankDays = new ArrayList<>();

    private BankDay currentDay;

    /**
     * @return new day.
     * @throws CanNotStartDayException if day is already started.
     */
    public BankDay startDay() throws CanNotStartDayException {
        if (this.currentDay == null || this.currentDay.completed()) {
            this.currentDay = new BankDay();
            this.currentDay.startDay();
            return this.currentDay;
        } else {
            throw new CanNotStartDayException("Finish current day before start another");
        }
    }

    /**
     * Complete day.
     */
    public void completeDay() {
        bankDays.add(this.currentDay);
        this.currentDay.completeDay();
    }

    /**
     * @param client who came.
     * @throws BankIsNotWorkingException if bank is not working.
     */
    public void clientCame(Client client) throws BankIsNotWorkingException {
        this.currentDay.clientCame(client);
    }

    /**
     * @param client who left.
     */
    public void clientLeft(Client client) {
        this.currentDay.clientLeft(client);
    }

    /**
     * Set current time.
     *
     * @param time to set.
     * @throws WrongTimeException if time is wrong.
     */
    public void setTime(Time time) throws WrongTimeException {
        this.currentDay.setTime(time);
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
     * @return statistic.
     */
    public List<ClientTimePeriod> calculateHighestLoadingPeriods() {
        ArrayList<ClientTimePeriod> periods = new ArrayList<>();

        // Reduce all client time periods in single array.
        Optional<List<ClientTimePeriod>> clientTimePeriodsOptional =
                this.bankDays.stream()
                        .map(BankDay::getClientTimePeriods)
                        .reduce(((clientTimePeriods1, clientTimePeriods2) -> new ArrayList<ClientTimePeriod>() {{
                            addAll(clientTimePeriods1);
                            addAll(clientTimePeriods2);
                        }}));

        if (!clientTimePeriodsOptional.isPresent()) {
            return periods;
        }

        // Make collection of TimePeriods from collection of ClientTimePeriod.
        List<TimePeriod> timePeriods = clientTimePeriodsOptional.get().stream()
                .map(clientTimePeriod -> new TimePeriod(clientTimePeriod.getCame(), clientTimePeriod.getLeft()))
                .collect(Collectors.toList());
        timePeriods.sort((o1, o2) -> o2.getFrom().compareTo(o1.getFrom()));

        // Find time positions when clients came or left.
//        Optional<ArrayList<Time>> times = timePeriods.stream().map(o -> new ArrayList<Time>() {{
//            add(o.getTo());
//            add(o.getFrom());
//        }}).reduce(((timePeriod, timePeriod2) -> new ArrayList<Time>(){{
//            addAll(timePeriod);
//            addAll(timePeriod2);
//        }}));
//        System.out.println("@@");
//        System.out.println(times.get());



//        Map<TimePeriod, Integer> timePeriodsLoading = new HashMap<>();
//        for (int hour = BankDay.BANK_DAY_START.getHour().getValue(); hour < BankDay.BANK_DAY_END.getHour().getValue(); hour++) {
//            for (int minute = 0; minute < 59; minute++) {
//                for (TimePeriod period : timePeriods) {
//                    if (period.getFrom().getHour().getValue() == hour && period.getFrom().getMinute().getValue() == minute) {
//                        timePeriodsLoading.putIfAbsent(period, 1);
//                    }
//                }
//            }
//        }

//        System.out.println(timePeriodsLoading);

        System.out.println(timePeriods);

        // Sum days loading statistics.
//        Map<Hour, Integer> bankLoadingStatistic = new HashMap<>();
//        for (int i = BankDay.BANK_DAY_START.getHour().getValue(); i <= BankDay.BANK_DAY_END.getHour().getValue(); i++) {
//            bankLoadingStatistic.put(new Hour(i), 0);
//        }
//        for (BankDay day : this.bankDays) {
//            for (Map.Entry<Hour, Integer> entry : day.getBankLoadingStatistic().entrySet()) {
//                bankLoadingStatistic.putIfAbsent(entry.getKey(), 0);
//                bankLoadingStatistic.put(entry.getKey(), bankLoadingStatistic.get(entry.getKey()) + entry.getValue());
//            }
//        }
//
//        Optional<Integer> maxLoading = bankLoadingStatistic.values().stream().max(Integer::compareTo);
//
//        // Group hours by maximum loading.
//        Map<Integer, List<Hour>> hoursByLoading = new HashMap<>();
//        for (Map.Entry<Hour, Integer> e : bankLoadingStatistic.entrySet()) {
//            hoursByLoading.putIfAbsent(e.getValue(), new ArrayList<>());
//            hoursByLoading.get(e.getValue()).add(e.getKey());
//        }
//
//        // Sort maximum loading hours.
//        List<Hour> maxLoadingHours = hoursByLoading.get(maxLoading.get());
//        maxLoadingHours.sort(Hour::compareTo);
//
//        // Perform periods.
//        HourPeriod hourPeriod = new HourPeriod(maxLoadingHours.get(0), new Hour(maxLoadingHours.get(0).getValue() + 1));
//        for (int i = 1; i < maxLoadingHours.size(); i++) {
//            Hour hour = maxLoadingHours.get(i);
//            if (hour.getValue() == hourPeriod.getTo().getValue()) {
//                hourPeriod = new HourPeriod(hourPeriod.getFrom(), new Hour(hour.getValue() + 1));
//            } else {
//                periods.add(hourPeriod);
//                hourPeriod = new HourPeriod(hour, new Hour(hour.getValue() + 1));
//            }
//        }
//
//        periods.add(hourPeriod);

        return periods;
    }
}
