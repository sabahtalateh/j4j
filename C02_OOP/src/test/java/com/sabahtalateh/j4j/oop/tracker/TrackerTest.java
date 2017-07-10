package com.sabahtalateh.j4j.oop.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * Tests.
 */
public class TrackerTest {
    @Test
    public void whenItemAddedToTrackerItPersistsInIt() {
        int maxItems = 2;
        Tracker tracker = new Tracker(maxItems);
        assertThat(tracker.getItems().length, is(0));

        tracker.add(new Item("Task1", "Description1"));
        assertThat(tracker.getItems().length, is(1));

        tracker.add(new Item("Task2", "Description2"));
        assertThat(tracker.getItems().length, is(2));
        assertThat(tracker.getItems()[0].getName(), is("Task1"));
        assertThat(tracker.getItems()[0].getDescription(), is("Description1"));
        assertThat(tracker.getItems()[1].getName(), is("Task2"));
        assertThat(tracker.getItems()[1].getDescription(), is("Description2"));
    }

    @Test
    public void whenTrackerOverflowsThanFirstElementIsDeleted() {
        int maxItems = 2;
        Tracker tracker = new Tracker(maxItems);
        assertThat(tracker.getItems().length, is(0));

        tracker.add(new Item("Task1", "Description1"));
        tracker.add(new Item("Task2", "Description2"));
        assertThat(tracker.getItems().length, is(2));
        assertThat(tracker.getItems()[0].getName(), is("Task1"));
        assertThat(tracker.getItems()[1].getName(), is("Task2"));

        tracker.add(new Item("Task3", "Description3"));
        assertThat(tracker.getItems().length, is(2));
        assertThat(tracker.getItems()[0].getName(), is("Task2"));
        assertThat(tracker.getItems()[1].getName(), is("Task3"));
    }

    @Test
    public void whenItemExistsInTrackerWeCanGetItById() {
        int maxItems = 5;
        Tracker tracker = new Tracker(maxItems);

        assertThat(tracker.findById("Non existing ID"), is(nullValue()));

        tracker.add(new Item("Task1", "Description1"));
        tracker.add(new Item("Task2", "Description2"));
        tracker.add(new Item("Task3", "Description3"));

        String item1ID = tracker.getItems()[0].getId();
        String item2ID = tracker.getItems()[1].getId();
        String item3ID = tracker.getItems()[2].getId();

        assertThat(tracker.findById(item1ID).getDescription(), is("Description1"));
        assertThat(tracker.findById(item3ID).getName(), is("Task3"));
        assertThat(tracker.findById(item2ID).getName(), is("Task2"));
    }

    @Test
    public void whenItemExistsInTrackerWeCanGetItByName() {
        int maxItems = 5;
        Tracker tracker = new Tracker(maxItems);
        assertThat(tracker.getItems().length, is(0));

        tracker.add(new Item("Task1", "Description1"));
        tracker.add(new Item("Task2", "Description2"));
        tracker.add(new Item("Task3", "Description3"));
        tracker.add(new Item("Task1", "Description4"));

        assertThat(tracker.findByName("Task1")[0].getDescription(), is("Description1"));
        assertThat(tracker.findByName("Task1")[1].getDescription(), is("Description4"));

        assertThat(tracker.findByName("Task3")[0].getDescription(), is("Description3"));
        assertThat(tracker.findByName("Task2")[0].getDescription(), is("Description2"));

        assertThat(tracker.findByName("Non existing task"), is(new Item[] {}));
    }

    @Test
    public void whenDeleteItemItDoesNotExistsInTrackerAnymore() {
        int maxItems = 5;
        Tracker tracker = new Tracker(maxItems);
        assertThat(tracker.getItems().length, is(0));

        tracker.add(new Item("Task1", "Description1"));
        tracker.add(new Item("Task1", "Description1"));
        tracker.add(new Item("Task1", "Description1"));
        assertThat(tracker.getItems().length, is(3));

        // Delete element.
        Item itemToDelete = tracker.getItems()[0];
        tracker.delete(itemToDelete);
        assertThat(tracker.getItems().length, is(2));
        assertThat(tracker.findById(itemToDelete.getId()), is(nullValue()));

        tracker.add(new Item("Task1", "Description1"));
        tracker.add(new Item("Task1", "Description1"));
        tracker.add(new Item("Task1", "Description1"));
        assertThat(tracker.getItems().length, is(5));

        // Delete one more element.
        itemToDelete = tracker.getItems()[3];
        tracker.delete(itemToDelete);
        assertThat(tracker.getItems().length, is(4));
        assertThat(tracker.findById(itemToDelete.getId()), is(nullValue()));
    }

    @Test
    public void whenItemBeenUpdatedItHasNewDescription() {
        int maxItems = 5;
        Tracker tracker = new Tracker(maxItems);

        tracker.add(new Item("Task1", "Description1"));
        tracker.add(new Item("Task2", "Description2"));
        tracker.add(new Item("Task3", "Description3"));

        Item itemToUpdate = tracker.findByName("Task2")[0];
        itemToUpdate.setName("New Name");
        itemToUpdate.setDescription("New Description");

        tracker.update(itemToUpdate);

        assertThat(tracker.findByName("Task2"), is(new Item[] {}));
        assertThat(tracker.findByName("New Name")[0].getDescription(), is("New Description"));
    }
}