package com.sabahtalateh.j4j.collections_advanced.iterator;

import java.util.Iterator;

/**
 * PrimeIterator.
 */
public class PrimeIterator implements Iterator<Integer> {

    private int[] numbers;

    private int position = 0;

    /**
     * @param numbers numbers.
     */
    public PrimeIterator(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * @return has next.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;

        for (int i = position; i < numbers.length; i++) {
            if (this.isPrime(numbers[i])) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * @return next.
     */
    @Override
    public Integer next() {
        for (int i = position; i < numbers.length; i++) {
            if (this.isPrime(numbers[i])) {
                this.position = i;
                break;
            }
        }
        return numbers[position++];
    }

    /**
     * @param n number.
     * @return checking result.
     */
    private boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
