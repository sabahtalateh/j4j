package com.sabahtalateh.j4j.oop.bank.time;

/**
 * Hour.
 */
public class Hour implements Comparable<Hour> {
    private final int value;

    /**
     * @param value hours amount.
     */
    public Hour(int value) {
        if (value > 23 || value < 0) {
            throw new IllegalArgumentException("Hour should be between 0 and 23");
        }
        this.value = value;
    }

    /**
     * @return value.
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }

    /**
     * @return value.
     */
    public int getValue() {
        return value;
    }

    /**
     * @param o to compare.
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

        Hour hour = (Hour) o;

        return value == hour.value;
    }

    /**
     * @return result.
     */
    @Override
    public int hashCode() {
        return value;
    }

    /**
     * @param o to compare.
     * @return result.
     */
    @Override
    public int compareTo(Hour o) {
        return ((Integer) this.getValue()).compareTo(o.getValue());
    }
}
