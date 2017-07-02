package com.sabahtalateh.j4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests.
 */
public class FactorialTest {

    @Test
    public void calc() {
        Factorial factorial = new Factorial();

        assertThat(factorial.calc(0), is(1));
        assertThat(factorial.calc(1), is(1));
        assertThat(factorial.calc(2), is(2));
        assertThat(factorial.calc(3), is(6));
        assertThat(factorial.calc(4), is(24));
        assertThat(factorial.calc(5), is(120));
        assertThat(factorial.calc(6), is(720));
        assertThat(factorial.calc(7), is(5040));
        assertThat(factorial.calc(10), is(3_628_800));
    }
}