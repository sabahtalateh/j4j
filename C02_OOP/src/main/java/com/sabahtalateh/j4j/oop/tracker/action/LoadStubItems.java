package com.sabahtalateh.j4j.oop.tracker.action;

import com.sabahtalateh.j4j.oop.tracker.Item;
import com.sabahtalateh.j4j.oop.tracker.Tracker;
import com.sabahtalateh.j4j.oop.tracker.io.IO;

/**
 * LoadStubItems.
 */
public class LoadStubItems implements Action {
    /**
     * @param tracker with tasks.
     * @param io      to interact.
     */
    @Override
    public void execute(Tracker tracker, IO io) {
        int amount = Math.abs(Integer.valueOf(io.ask("How much? ")));

        for (int i = 0; i < amount; i++) {
            Item item = new Item("Task " + (i + 1), "Description " + (i + 1));
            tracker.add(item);
        }
        io.answer(String.format("%s Stub tasks loaded..", amount));
    }
}
