package com.sabahtalateh.j4j.oop.bank.time;

/**
 * Time.
 */
public class Time {
    private final Hour hour;
    private final Minute minute;

    /**
     * @param hour    hours in time.
     * @param minute minute in time.
     */
    public Time(Hour hour, Minute minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * @param hour    hours in time.
     * @param minute minute in time.
     */
    public Time(int hour, int minute) {
        this.hour = new Hour(hour);
        this.minute = new Minute(minute);
    }

    /**
     * @return time.
     */
    @Override
    public String toString() {
        return "T{" + this.hour + ":" + this.minute + '}';
    }

    /**
     * @return hour.
     */
    public Hour getHour() {
        return this.hour;
    }

    /**
     * @return minute.
     */
    public Minute getMinute() {
        return this.minute;
    }
}
