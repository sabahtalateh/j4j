package com.sabahtalateh.j4j.oop.tracker.action;

import com.sabahtalateh.j4j.oop.tracker.Item;
import com.sabahtalateh.j4j.oop.tracker.Tracker;
import com.sabahtalateh.j4j.oop.tracker.io.IO;

/**
 * DeleteItem.
 */
public class DeleteItem implements Action {
    /**
     * @param tracker with tasks.
     * @param io      to interact.
     */
    @Override
    public void execute(Tracker tracker, IO io) {
        String id = io.ask("Type id of item to delete: ");
        Item item = tracker.findById(id);
        if (item != null) {
            tracker.delete(item);
            io.answer("Deleted.");
        } else {
            io.answer("No such item.");
        }
    }
}
