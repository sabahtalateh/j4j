package com.sabahtalateh.j4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests.
 */
public class PyramidTest {

    @Test
    public void paint0HeightPyramid() throws Exception {
        Pyramid pyramid = new Pyramid();

        String expected = "";

        assertThat(pyramid.paint(0), is(expected));
    }

    @Test
    public void paint1HeightPyramid() throws Exception {
        Pyramid pyramid = new Pyramid();

        String expected = "^";

        assertThat(pyramid.paint(1), is(expected));
    }

    @Test
    public void paint2HeightPyramid() throws Exception {
        Pyramid pyramid = new Pyramid();
        String lineSeparator = System.getProperty("line.separator");

        String expected =         " ^ "
                + lineSeparator + "^^^";

        assertThat(pyramid.paint(2), is(expected));
    }

    @Test
    public void paint3HeightPyramid() throws Exception {
        Pyramid pyramid = new Pyramid();
        String lineSeparator = System.getProperty("line.separator");

        String expected =         "  ^  "
                + lineSeparator + " ^^^ "
                + lineSeparator + "^^^^^";

        assertThat(pyramid.paint(3), is(expected));
    }

    @Test
    public void paint5HeightPyramid() throws Exception {
        Pyramid pyramid = new Pyramid();
        String lineSeparator = System.getProperty("line.separator");

        String expected =         "    ^    "
                + lineSeparator + "   ^^^   "
                + lineSeparator + "  ^^^^^  "
                + lineSeparator + " ^^^^^^^ "
                + lineSeparator + "^^^^^^^^^";

        assertThat(pyramid.paint(5), is(expected));
    }
}