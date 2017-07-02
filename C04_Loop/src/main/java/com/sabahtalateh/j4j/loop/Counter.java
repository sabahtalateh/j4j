package com.sabahtalateh.j4j.loop;

/**
 * Counter.
 */
public class Counter {
    /**
     * @param start start of range.
     * @param end end of range (including).
     * @return sum of even from start to end of range, including end.
     */
    public int sumEven(int start, int end) {
        int sum = 0;

        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }

        return sum;
    }
}
