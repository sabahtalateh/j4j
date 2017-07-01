package com.sabahtalateh.j4j.condition;

import org.junit.Test;

import static org.junit.Assert.*;
//import static org.hamcrest.core.Is.is;

/**
 * Tests.
 */
public class PointTest {
    @Test
    public void yEqualsXTest() throws Exception {
        // y = x function; a = 1, b = 0
        Function function = new Function(1, 0);

        assertTrue(new Point(0, 0).belongsToFunction(function.getA(), function.getB()));
        assertTrue(new Point(1, 1).belongsToFunction(function.getA(), function.getB()));
        assertTrue(new Point(100, 100).belongsToFunction(function.getA(), function.getB()));
        assertFalse(new Point(0, 5).belongsToFunction(function.getA(), function.getB()));
    }

    @Test
    public void yEquals2x() {
        // y = 2 * x; a = 2, b = 0
        Function function = new Function(2, 0);

        assertTrue(new Point(0, 0).belongsToFunction(function.getA(), function.getB()));
        assertTrue(new Point(1, 2).belongsToFunction(function.getA(), function.getB()));
        assertFalse(new Point(1, 1).belongsToFunction(function.getA(), function.getB()));
    }

    @Test
    public void yEquals2xPlus3() {
        // y = 2 * x + 4; a = 2, b = 4
        Function function = new Function(2, 4);

        assertFalse(new Point(0, 0).belongsToFunction(function.getA(), function.getB()));
        assertFalse(new Point(12, 32).belongsToFunction(function.getA(), function.getB()));
        assertTrue(new Point(0, 4).belongsToFunction(function.getA(), function.getB()));
        assertTrue(new Point(2, 8).belongsToFunction(function.getA(), function.getB()));
        assertTrue(new Point(4, 12).belongsToFunction(function.getA(), function.getB()));
    }

    /**
     * Function.
     */
    class Function {
        /**
         * a, b - coefficients.
         */
        private int a, b;

        /**
         * @param a coefficient.
         * @param b coefficient.
         */
        Function(int a, int b) {
            this.a = a;
            this.b = b;
        }

        /**
         * @return coefficient.
         */
        int getA() {
            return a;
        }

        /**
         * @return coefficient.
         */
        int getB() {
            return b;
        }
    }
}
