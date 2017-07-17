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

    /**
     * @return string.
     */
    @Override
    public String toString() {
        return "HourPeriod{start=" + start + ", end=" + end + '}';
    }

    /**
     * @param o to check.
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

        HourPeriod that = (HourPeriod) o;

        if (start != null ? !start.equals(that.start) : that.start != null) {
            return false;
        }
        return end != null ? end.equals(that.end) : that.end == null;
    }

    /**
     * @return hash code.
     */
    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}
