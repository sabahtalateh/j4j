package com.sabahtalateh.j4j.loop;

/**
 * Factorial.
 */
public class Factorial {

    /**
     * @param n number.
     * @return factorial.
     */
    public int calc(int n) {
        // Factorial of 0.
        int fact = 1;

        for (int i = 1; i <= n; i++) {
            fact *= i;
        }

        return fact;
    }
}
