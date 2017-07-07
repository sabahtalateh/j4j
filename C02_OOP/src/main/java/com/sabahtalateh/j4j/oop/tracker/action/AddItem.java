package com.sabahtalateh.j4j.oop.tracker.action;

import com.sabahtalateh.j4j.oop.tracker.Item;
import com.sabahtalateh.j4j.oop.tracker.Tracker;
import com.sabahtalateh.j4j.oop.tracker.io.IO;

/**
 * AddItem.
 */
public class AddItem implements Action {
    /**
     * @param tracker with tasks.
     * @param io      to interact.
     */
    @Override
    public void execute(Tracker tracker, IO io) {
        Item item = new Item(io.ask("[Name]: "), io.ask("[Description]: "));
        tracker.add(item);
        io.answer(String.format("Item [%s] added.", item.getName()));
    }
}
