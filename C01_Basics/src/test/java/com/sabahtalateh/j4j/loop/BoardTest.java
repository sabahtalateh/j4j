package com.sabahtalateh.j4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests.
 */
public class BoardTest {
    @Test
    public void paint3X3Board() {
        Board board = new Board();

        String lineSeparator = System.getProperty("line.separator");

        String board3X3 = "X X" + lineSeparator + " X " + lineSeparator + "X X";

        assertThat(board.paint(3, 3), is(board3X3));
    }

    @Test
    public void paint5X5Board() {
        Board board = new Board();

        String lineSeparator = System.getProperty("line.separator");

        String board5X5 = "X X X" + lineSeparator + " X X " + lineSeparator + "X X X"  + lineSeparator + " X X " + lineSeparator + "X X X";

        assertThat(board.paint(5, 5), is(board5X5));
    }
}