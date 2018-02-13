package com.sabahtalateh.j4j.algo;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * BracketCheckerTest.
 */
public class BracketCheckerTest {

    @Test
    public void testEmpty() throws Exception {
        assertThat(new BracketChecker().check(""), is(true));
    }

    @Test
    public void testSimpleBrackets() throws Exception {
        assertThat(new BracketChecker().check("()"), is(true));
        assertThat(new BracketChecker().check("()()"), is(true));
        assertThat(new BracketChecker().check("()()()"), is(true));
        assertThat(new BracketChecker().check("(()()())"), is(true));
        assertThat(new BracketChecker().check("( ( () (()) ) () )"), is(true));

        assertThat(new BracketChecker().check("("), is(false));
        assertThat(new BracketChecker().check(")"), is(false));

        assertThat(new BracketChecker().check("(()(())"), is(false));
    }

    @Test
    public void testComplexBrackets() throws Exception {
        assertThat(new BracketChecker().check("([])"), is(true));
        assertThat(new BracketChecker().check("([])[]{()[]}"), is(true));

        assertThat(new BracketChecker().check("(}"), is(false));
        assertThat(new BracketChecker().check("({[])"), is(false));
        assertThat(new BracketChecker().check("([])[]{(()[]}"), is(false));
    }
}