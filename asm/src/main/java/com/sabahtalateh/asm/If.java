package com.sabahtalateh.asm;

/**
 * If.
 */
public class If {

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
}
