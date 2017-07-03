package com.sabahtalateh.j4j.condition;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Tests.
 */
public class PointTest {
    @Test
    public void yEqualsXTest() throws Exception {
        // y = x linearFunction; a = 1, b = 0
        int a = 1, b = 0;

        assertThat(new Point(0, 0).belongsToFunction(a, b), is(true));
        assertThat(new Point(1, 1).belongsToFunction(a, b), is(true));
        assertThat(new Point(100, 100).belongsToFunction(a, b), is(true));
        assertThat(new Point(0, 5).belongsToFunction(a, b), is(false));
    }

    @Test
    public void yEquals2x() {
        // y = 2 * x; a = 2, b = 0
        int a = 2, b = 0;

        assertThat(new Point(0, 0).belongsToFunction(a, b), is(true));
        assertThat(new Point(1, 2).belongsToFunction(a, b), is(true));
        assertThat(new Point(1, 1).belongsToFunction(a, b), is(false));
    }

    @Test
    public void yEquals2xPlus3() {
        // y = 2 * x + 4; a = 2, b = 4
        int a = 2, b = 4;

        assertThat(new Point(12, 32).belongsToFunction(a, b), is(false));
        assertThat(new Point(0, 4).belongsToFunction(a, b), is(true));
        assertThat(new Point(2, 8).belongsToFunction(a, b), is(true));
        assertThat(new Point(4, 12).belongsToFunction(a, b), is(true));
    }
}
