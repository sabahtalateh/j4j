package com.sabahtalateh.j4j.oop.bank.time;

/**
 * TimePeriod.
 */
public class TimePeriod {
    private Time from;

    private Time to;

    /**
     * @param from from.
     * @param to   to.
     */
    public TimePeriod(Time from, Time to) {
        this.from = from;
        this.to = to;
    }


    /**
     * @return from.
     */
    public Time getFrom() {
        return from;
    }

    /**
     * @return to.
     */
    public Time getTo() {
        return to;
    }

    /**
     * @return string.
     */
    @Override
    public String toString() {
        return "TP<" + from + "," + to + '>';
    }

    /**
     * @param o object.
     * @return result.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TimePeriod that = (TimePeriod) o;

        if (from != null ? !from.equals(that.from) : that.from != null) {
            return false;
        }
        return to != null ? to.equals(that.to) : that.to == null;
    }

    /**
     * @return hash code.
     */
    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}
