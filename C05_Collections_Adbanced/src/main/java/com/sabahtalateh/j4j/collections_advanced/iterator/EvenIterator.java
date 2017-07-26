package com.sabahtalateh.j4j.collections_advanced.iterator;

import java.util.Iterator;

/**
 * EvenIterator.
 */
public class EvenIterator implements Iterator<Integer> {

    int[] numbers;
    int position = 0;

    /**
     * @param numbers numbers.
     */
    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * @return check if next exists.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = position + 1; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
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
        for (int i = position + 1; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                position = i;
                break;
            }
        }
        return numbers[position];
    }
}
