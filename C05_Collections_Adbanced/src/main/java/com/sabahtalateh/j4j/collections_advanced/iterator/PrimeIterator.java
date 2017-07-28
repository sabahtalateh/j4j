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
        return nextPrimePosition() != -1;
    }

    /**
     * @return next.
     */
    @Override
    public Integer next() {
        this.position = nextPrimePosition();
        return numbers[position++];
    }

    /**
     * @param n number.
     * @return checking result.
     */
    private boolean isPrime(int n) {
        boolean isPrime = n != 1;
        for (int i = 2; i < n / 2 + 1; i++) {
            if (n % i == 0) {
                isPrime = false;
            }
        }
        return isPrime;
    }

    /**
     * @return next prime position or -1 if no primes.
     */
    private int nextPrimePosition() {
        int primePosition = -1;
        for (int i = this.position; i < numbers.length; i++) {
            if (this.isPrime(numbers[i])) {
                primePosition = i;
                break;
            }
        }
        return primePosition;
    }
}
