package com.sabahtalateh.asm;

/**
 * If.
 */
public class If {

    private static final int I = 81;

    /**
     * @param left  left.
     * @param right right.
     * @return max.
     */
    public static int max(final int left, final int right) {
        if (left >= right) {
            return left;
        } else {
            return right;
        }
    }

    /**
     * @param a number.
     * @return module.
     */
    public static int mod(final int a) {
        final int result;
        if (a >= 0) {
            result = a;
        } else {
            result = -a;
        }
        return result;
    }

    /**
     * @param left  left.
     * @param right right.
     * @return max.
     */
    public static int min(final int left, final int right) {
        if (left < right) {
            return left;
        } else {
            return right;
        }
    }

    /**
     * @param a a.
     * @param b b.
     * @param c c.
     * @return min.
     */
    public static int min2(int a, int b, int c) {
        if (a <= b && a <= c) {
            return a;
        }
        if (b <= c) return b;
        return c;
    }

    /**
     * @param a a.
     * @param b b.
     * @return max.
     */
    public static double max(double a, double b) {
        if (a >= b) {
            return a;
        } else {
            return b;
        }
    }
}
