package com.sabahtalateh.j4j.max;

/**
 * This is very useful class.
 * You can find maximum of two numbers with it.
 */
public class Max {
    /**
     * @param a first number
     * @param b first number
     * @return maximum
     */
    public int max(int a, int b) {
        return a > b ? a : b;
    }

    /**
     * @param a first number
     * @param b second  number
     * @param c third number
     * @return maximum
     */
    public int max(int a, int b, int c) {
        return max(max(a, b), c);
    }
}
