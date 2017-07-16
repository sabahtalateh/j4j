package com.sabahtalateh.j4j.oop.bank.time;

/**
 * Minute.
 */
public class Minute implements Comparable<Minute> {
    private final int value;

    /**
     * @param value minutes amount.
     */
    public Minute(int value) {
        if (value > 59 || value < 0) {
            throw new IllegalArgumentException("Minute should be between 0 and 59");
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
    public int compareTo(Minute o) {
        return ((Integer) this.getValue()).compareTo(o.getValue());
    }
}
