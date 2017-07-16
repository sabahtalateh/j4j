package com.sabahtalateh.j4j.oop.bank.time;

/**
 * HourPeriod.
 */
public class HourPeriod {
    private final Hour start;
    private final Hour end;

    /**
     * @param start of period.
     * @param end   of period.
     */
    public HourPeriod(Hour start, Hour end) {
        this.start = start;
        this.end = end;
    }

    /**
     * @return hour.
     */
    public Hour getStart() {
        return start;
    }

    /**
     * @return hour.
     */
    public Hour getEnd() {
        return end;
    }
}
