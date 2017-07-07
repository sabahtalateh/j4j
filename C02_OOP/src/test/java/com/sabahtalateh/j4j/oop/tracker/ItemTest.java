package com.sabahtalateh.j4j.oop.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests.
 */
public class ItemTest {
    @Test
    public void addCommentTest() throws Exception {
        int maxComments = 2;
        Item item = new Item("Go to the shop", "Description", maxComments);
        assertThat(item.getComments().length, is(0));

        item.addComment("Good");
        assertThat(item.getComments().length, is(1));
        assertThat(item.getComments()[0], is("Good"));

        item.addComment("Cool");
        assertThat(item.getComments().length, is(2));
        assertThat(item.getComments()[0], is("Good"));
        assertThat(item.getComments()[1], is("Cool"));

        item.addComment("Bad");
        assertThat(item.getComments().length, is(2));
        assertThat(item.getComments()[0], is("Cool"));
        assertThat(item.getComments()[1], is("Bad"));

        item.addComment("I will never do it");
        assertThat(item.getComments().length, is(2));
        assertThat(item.getComments()[0], is("Bad"));
        assertThat(item.getComments()[1], is("I will never do it"));
    }
}