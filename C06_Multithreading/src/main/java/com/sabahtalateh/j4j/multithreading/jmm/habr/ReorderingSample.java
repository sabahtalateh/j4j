package com.sabahtalateh.j4j.multithreading.jmm.habr;

/**
 * ReorderingSample.
 */
public class ReorderingSample {
    boolean first = false;
    boolean second = false;

    /**
     * Set values.
     */
    void setValues() {
        first = true;
        second = true;
    }

    /**
     * Check values.
     */
    void checkValues() {
        while (!second) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assert first;
    }
}
