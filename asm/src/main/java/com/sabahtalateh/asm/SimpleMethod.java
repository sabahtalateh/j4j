package com.sabahtalateh.asm;

/**
 * SimpleMethod.
 */
public class SimpleMethod {
    /**
     * No operations.
     */
    public static void noOp() {
    }

    /**
     * @return int.
     */
    public static int returnConst() {
        return 2;
    }

    /**
     * @param a a.
     * @param b b.
     * @return a.
     */
    public static int returnFirst(int a, int b) {
        return a;
    }
}
