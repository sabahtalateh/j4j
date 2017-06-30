package com.sabahtalateh.j4j;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;

import static org.junit.Assert.assertThat;

/**
 * Here we test all the stuff.
 */
public class CalculateTest {

    @Test
    public void whenAddOneToOneThenGetTwo() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculate.main(null);
        assertThat(out.toString(), is(String.format("Hello%s", System.getProperty("line.separator"))));
    }
}
