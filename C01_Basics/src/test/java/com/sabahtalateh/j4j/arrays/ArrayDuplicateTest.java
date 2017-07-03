package com.sabahtalateh.j4j.arrays;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests.
 */
public class ArrayDuplicateTest {
    @Test
    public void deduplicate() throws Exception {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();

        assertThat(arrayDuplicate.deduplicate(new String[]{}), is(new String[]{}));
        assertThat(arrayDuplicate.deduplicate(new String[]{"Hello"}), is(new String[]{"Hello"}));
        assertThat(
                arrayDuplicate.deduplicate(new String[]{"Hello", "Hello"}),
                is(new String[]{"Hello"})
        );

        assertThat(
                arrayDuplicate.deduplicate(new String[]{"Hello", "Hello", "Hello"}),
                is(new String[]{"Hello"})
        );

        assertThat(
                arrayDuplicate.deduplicate(new String[]{"Hello", "Hello", "Hello", "Hello"}),
                is(new String[]{"Hello"})
        );

        assertThat(
                arrayDuplicate.deduplicate(new String[]{"Hello", "Hello", "World", "Hello", "Hello"}),
                is(new String[]{"Hello", "World"})
        );

        assertThat(
                arrayDuplicate.deduplicate(new String[]{"Hello", "Hello", "World", "World", "Hello", "Hello"}),
                is(new String[]{"Hello", "World"})
        );

        assertThat(
                arrayDuplicate.deduplicate(new String[]{"Hello", "World", "Hello", "World", "Super", "World", "Hello", "Hello"}),
                is(new String[]{"Hello", "World", "Super"})
        );

        assertThat(
                arrayDuplicate.deduplicate(new String[]{"1", "4", "1", "1", "2", "4", "12", "1", "4", "12", "3", "1", "2"}),
                is(new String[]{"1", "4", "2", "12", "3"})
        );

    }

}