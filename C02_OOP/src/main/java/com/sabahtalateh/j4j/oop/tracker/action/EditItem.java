package com.sabahtalateh.j4j.oop.tracker.action;

import com.sabahtalateh.j4j.oop.tracker.Item;
import com.sabahtalateh.j4j.oop.tracker.Tracker;
import com.sabahtalateh.j4j.oop.tracker.io.IO;

/**
 * Edit Item.
 */
public class EditItem implements Action {
    /**
     * @param tracker with tasks.
     * @param io      to interact.
     */
    @Override
    public void execute(Tracker tracker, IO io) {
        String id = io.ask("Enter [ID] of item to edit: ");
        Item item = tracker.findById(id);

        if (item == null) {
            io.answer("There is no such item in tracker.");
            return;
        }

        item.setName(io.ask("New [Name]: "));
        item.setDescription(io.ask("New [Description]: "));
        tracker.update(item);
        io.answer(String.format("Item [%s] updated.", item.getId()));
    }
}
