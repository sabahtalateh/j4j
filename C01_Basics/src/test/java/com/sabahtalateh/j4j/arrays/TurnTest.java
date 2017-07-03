package com.sabahtalateh.j4j.arrays;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 */
public class TurnTest {

    @Test
    public void revert() throws Exception {
        Turn turn = new Turn();

        assertThat(turn.revert(new int[]{}), is(new int[]{}));
        assertThat(turn.revert(new int[]{1}), is(new int[]{1}));
        assertThat(turn.revert(new int[]{1, 2}), is(new int[]{2, 1}));
        assertThat(turn.revert(new int[]{1, 2, 3}), is(new int[]{3, 2, 1}));
        assertThat(turn.revert(new int[]{1, 2, 3, 4, 5, 6}), is(new int[]{6, 5, 4, 3, 2, 1}));
    }
}
