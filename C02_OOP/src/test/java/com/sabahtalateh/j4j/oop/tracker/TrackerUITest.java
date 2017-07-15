package com.sabahtalateh.j4j.oop.tracker;

import com.sabahtalateh.j4j.oop.tracker.io.StubIO;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TrackerUITest.
 */
public class TrackerUITest {

    private StartUI ui;

    private StubIO io;

    private Tracker tracker;

    @Before
    public void setUp() throws Exception {
        this.tracker = new Tracker();
        this.io = new StubIO();
        this.ui = new StartUI(this.io, this.tracker);
    }

    @Test
    public void testExit() {
        this.io.actionSequence = new String[]{String.valueOf(Menu.EXIT.ordinal())};
        this.ui.run();
        String[] answers = this.io.getAnswers();
        assertThat(answers[answers.length - 1], is("Bue.."));
    }

    @Test
    public void testLoadStubs() {
        this.io.actionSequence = new String[]{
                String.valueOf(Menu.LOAD_STUBS.ordinal()),
                String.valueOf(12),
                String.valueOf(Menu.EXIT.ordinal()),
        };
        this.ui.run();

        assertThat(this.tracker.getItems().size(), is(12));
    }

    @Test
    public void testAddItems() {
        this.io.actionSequence = new String[]{
                String.valueOf(Menu.ADD_ITEM.ordinal()),
                "Ivan Bulkin",
                "Buy some cubes.",
                String.valueOf(Menu.ADD_ITEM.ordinal()),
                "Ilya Palkin",
                "Sold some triangles.",
                String.valueOf(Menu.EXIT.ordinal())
        };
        this.ui.run();

        assertThat(this.tracker.getItems().size(), is(2));
    }

    @Test
    public void testFindItemsByName() {
        String[] actions = new String[]{
                String.valueOf(Menu.ADD_ITEM.ordinal()),
                "Ivan Bulkin",
                "Buy some cubes.",
                String.valueOf(Menu.ADD_ITEM.ordinal()),
                "Ilya Palkin",
                "Sold some triangles.",
                String.valueOf(Menu.ADD_ITEM.ordinal()),
                "Ivan Pupovetskiy",
                "Make all things work.",
                String.valueOf(Menu.FIND_BY_NAME.ordinal()),
                "Ivan",
                String.valueOf(Menu.EXIT.ordinal()),
        };
        this.io.setActionSequence(actions);
        this.ui.run();
        assertThat(this.tracker.getItems().size(), is(3));

        String[] answers = this.io.getAnswers();
        assertThat(answers[answers.length - 2], is("2 items printed."));
    }

    @Test
    public void testFindItemsById() {
        String[] actions = new String[]{
                String.valueOf(Menu.ADD_ITEM.ordinal()),
                "Ivan Bulkin",
                "Buy some cubes.",
                String.valueOf(Menu.ADD_ITEM.ordinal()),
                "Ilya Palkin",
                "Sold some triangles.",
                String.valueOf(Menu.ADD_ITEM.ordinal()),
                "Ivan Pupovetskiy",
                "Make all things work.",
                String.valueOf(Menu.EXIT.ordinal()),
        };
        this.io.setActionSequence(actions);
        this.ui.run();

        Item item = tracker.getItems().get(0);
        String id = item.getId();

        actions = new String[]{
                String.valueOf(Menu.FIND_BY_ID.ordinal()),
                id,
                String.valueOf(Menu.EXIT.ordinal()),
        };
        this.io.setActionSequence(actions);
        this.ui.run();

        String[] answers = this.io.getAnswers();
        String tasksNameAnswer = answers[answers.length - 5];
        assertThat(tasksNameAnswer.contains("Ivan Bulkin"), is(true));
    }

    @Test
    public void testEditItem() {
        String[] actions = new String[]{
                String.valueOf(Menu.ADD_ITEM.ordinal()),
                "Ivan Bulkin",
                "Buy some cubes.",
                String.valueOf(Menu.EXIT.ordinal()),
        };
        this.io.setActionSequence(actions);
        this.ui.run();

        Item item = tracker.getItems().get(0);
        String id = item.getId();

        actions = new String[]{
                String.valueOf(Menu.EDIT_ITEM.ordinal()),
                id,
                "Ivan Pirogov",
                "Buy some spheres.",
                String.valueOf(Menu.EXIT.ordinal()),
        };
        this.io.setActionSequence(actions);
        this.ui.run();

        assertThat(tracker.findById(id).getName(), is("Ivan Pirogov"));
        assertThat(tracker.findById(id).getDescription(), is("Buy some spheres."));
    }

    @Test
    public void testDeleteItem() {
        String[] actions = new String[]{
                String.valueOf(Menu.ADD_ITEM.ordinal()),
                "Ivan Bulkin",
                "Buy some cubes.",
                String.valueOf(Menu.ADD_ITEM.ordinal()),
                "Ilya Palkin",
                "Sold some triangles.",
                String.valueOf(Menu.ADD_ITEM.ordinal()),
                "Ivan Pupovetskiy",
                "Make all things work.",
                String.valueOf(Menu.EXIT.ordinal()),
        };
        this.io.setActionSequence(actions);
        this.ui.run();

        Item item = tracker.getItems().get(0);
        String id = item.getId();

        assertThat(tracker.findById(id), is(notNullValue()));

        actions = new String[]{
                String.valueOf(Menu.DELETE_ITEM.ordinal()),
                id,
                String.valueOf(Menu.EXIT.ordinal()),
        };
        this.io.setActionSequence(actions);
        this.ui.run();

        assertThat(tracker.findById(id), is(nullValue()));
    }
}