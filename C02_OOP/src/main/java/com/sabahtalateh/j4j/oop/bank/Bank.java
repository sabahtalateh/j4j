package com.sabahtalateh.j4j.oop.bank;

import com.sabahtalateh.j4j.oop.bank.time.Hour;
import com.sabahtalateh.j4j.oop.bank.time.Minute;
import com.sabahtalateh.j4j.oop.bank.time.Time;
import com.sabahtalateh.j4j.oop.bank.time.TimePeriod;

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
    public List<TimePeriod> calculateHighestLoadingPeriods() {
        List<TimePeriod> emptyResult = new ArrayList<TimePeriod>() {{
            add(new TimePeriod(BankDay.BANK_DAY_START, BankDay.BANK_DAY_END));
        }};

        // Reduce all client time periods in single array.
        Optional<List<ClientTimePeriod>> clientTimes =
                this.bankDays.stream()
                        .map(BankDay::getClientTimePeriods)
                        .reduce(((clientTimePeriods1, clientTimePeriods2) -> new ArrayList<ClientTimePeriod>() {{
                            addAll(clientTimePeriods1);
                            addAll(clientTimePeriods2);
                        }}));

        if (!clientTimes.isPresent()) {
            return emptyResult;
        }

        // Make collection of TimePeriods from collection of ClientTimePeriod.
        List<TimePeriod> timePeriods = clientTimes.get().stream()
                .map(clientTimePeriod -> new TimePeriod(clientTimePeriod.getCame(), clientTimePeriod.getLeft()))
                .collect(Collectors.toList());

        Collection<Collection<Time>> timePeriodsCollection = timePeriods.stream()
                .map(o -> new ArrayList<Time>() {{
                    add(o.getFrom());
                    add(o.getTo());
                }}).collect(Collectors.toList());

        // Make times set from time periods collection.
        Optional<Collection<Time>> times = timePeriodsCollection.stream()
                .reduce((times1, times2) -> new TreeSet<Time>(Comparator.reverseOrder()) {{
                    addAll(times1);
                    addAll(times2);
                }});

        if (!times.isPresent()) {
            return emptyResult;
        }

        Map<TimePeriod, Integer> loadingPeriods = new TreeMap<>((o1, o2) -> o2.getFrom().compareTo(o1.getFrom()));

        if (times.get().size() <= 1) {
            return emptyResult;
        }

        List<Time> timesList = new ArrayList<Time>() {{
            addAll(times.get());
        }};

        for (int i = 0; i < timesList.size() - 1; i++) {
            Time current = timesList.get(i);
            Time next = timesList.get(i + 1);
            loadingPeriods.put(new TimePeriod(current, next), 0);
        }

        for (TimePeriod timePeriod : timePeriods) {
            for (Map.Entry<TimePeriod, Integer> e : loadingPeriods.entrySet()) {
                if (timePeriod.getFrom().compareTo(e.getKey().getFrom()) >= 0 && timePeriod.getTo().compareTo(e.getKey().getTo()) <= 0) {
                    e.setValue(e.getValue() + 1);
                }
            }
        }

        Optional<Integer> maxLoading = loadingPeriods.values().stream().max(Integer::compareTo);

        if (!maxLoading.isPresent()) {
            return emptyResult;
        }

        return loadingPeriods.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxLoading.get()))
                .map(Map.Entry::getKey)
                .sorted((o1, o2) -> o2.getFrom().compareTo(o1.getFrom()))
                .collect(Collectors.toList());
    }
}
