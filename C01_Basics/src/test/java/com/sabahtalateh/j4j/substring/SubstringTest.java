package com.sabahtalateh.j4j.substring;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests.
 */
public class SubstringTest {
    @Test
    public void contains() {
        Substring substring = new Substring();

        assertThat(substring.contains("", ""), is(true));
        assertThat(substring.contains("Hello", "Hello"), is(true));
        assertThat(substring.contains("Hello Super World", "Hell"), is(true));
        assertThat(substring.contains("Hello Super World", "World"), is(true));
        assertThat(substring.contains("Hello Super World", "Super"), is(true));
        assertThat(substring.contains("Hello Super World", " "), is(true));

        assertThat(substring.contains("Hello Super World", "Goodbye"), is(false));
        assertThat(substring.contains("1", "11"), is(false));
    }
}
