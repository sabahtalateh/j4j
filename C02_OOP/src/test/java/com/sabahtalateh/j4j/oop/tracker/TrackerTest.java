package com.sabahtalateh.j4j.oop.tracker;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests.
 */
public class TrackerTest {
    @Test
    public void whenItemAddedToTrackerItPersistsInIt() {
        Tracker tracker = new Tracker();
        assertThat(tracker.getItems().size(), is(0));

        tracker.add(new Item("Task1", "Description1"));
        assertThat(tracker.getItems().size(), is(1));

        tracker.add(new Item("Task2", "Description2"));
        assertThat(tracker.getItems().size(), is(2));
        assertThat(tracker.getItems().get(0).getName(), is("Task1"));
        assertThat(tracker.getItems().get(0).getDescription(), is("Description1"));
        assertThat(tracker.getItems().get(1).getName(), is("Task2"));
        assertThat(tracker.getItems().get(1).getDescription(), is("Description2"));
    }

    @Test
    public void whenItemExistsInTrackerWeCanGetItById() {
        Tracker tracker = new Tracker();

        assertThat(tracker.findById("Non existing ID"), is(nullValue()));

        tracker.add(new Item("Task1", "Description1"));
        tracker.add(new Item("Task2", "Description2"));
        tracker.add(new Item("Task3", "Description3"));

        String item1ID = tracker.getItems().get(0).getId();
        String item2ID = tracker.getItems().get(1).getId();
        String item3ID = tracker.getItems().get(2).getId();

        assertThat(tracker.findById(item1ID).getDescription(), is("Description1"));
        assertThat(tracker.findById(item3ID).getName(), is("Task3"));
        assertThat(tracker.findById(item2ID).getName(), is("Task2"));
    }

    @Test
    public void whenItemExistsInTrackerWeCanGetItByName() {
        Tracker tracker = new Tracker();
        assertThat(tracker.getItems().size(), is(0));

        tracker.add(new Item("Task1", "Description1"));
        tracker.add(new Item("Task2", "Description2"));
        tracker.add(new Item("Task3", "Description3"));
        tracker.add(new Item("Task1", "Description4"));

        assertThat(tracker.findByName("Task1").get(0).getDescription(), is("Description1"));
        assertThat(tracker.findByName("Task1").get(1).getDescription(), is("Description4"));
        assertThat(tracker.findByName("Task3").get(0).getDescription(), is("Description3"));
        assertThat(tracker.findByName("Task2").get(0).getDescription(), is("Description2"));

        assertThat(tracker.findByName("Non existing task"), is(new ArrayList<>()));
    }

    @Test
    public void whenDeleteItemItDoesNotExistsInTrackerAnymore() {
        Tracker tracker = new Tracker();
        assertThat(tracker.getItems().size(), is(0));

        tracker.add(new Item("Task1", "Description1"));
        tracker.add(new Item("Task1", "Description1"));
        tracker.add(new Item("Task1", "Description1"));
        assertThat(tracker.getItems().size(), is(3));

        // Delete element.
        Item itemToDelete = tracker.getItems().get(0);
        tracker.delete(itemToDelete);
        assertThat(tracker.getItems().size(), is(2));
        assertThat(tracker.findById(itemToDelete.getId()), is(nullValue()));

        tracker.add(new Item("Task1", "Description1"));
        tracker.add(new Item("Task1", "Description1"));
        tracker.add(new Item("Task1", "Description1"));
        assertThat(tracker.getItems().size(), is(5));

        // Delete one more element.
        itemToDelete = tracker.getItems().get(0);
        tracker.delete(itemToDelete);
        assertThat(tracker.getItems().size(), is(4));
        assertThat(tracker.findById(itemToDelete.getId()), is(nullValue()));
    }

    @Test
    public void whenItemBeenUpdatedItHasNewDescription() {
        Tracker tracker = new Tracker();

        tracker.add(new Item("Task1", "Description1"));
        tracker.add(new Item("Task2", "Description2"));
        tracker.add(new Item("Task3", "Description3"));

        Item itemToUpdate = tracker.findByName("Task2").get(0);
        itemToUpdate.setName("New Name");
        itemToUpdate.setDescription("New Description");

        tracker.update(itemToUpdate);

        assertThat(tracker.findByName("Task2"), is(new ArrayList<>()));
        assertThat(tracker.findByName("New Name").get(0).getDescription(), is("New Description"));
    }
}