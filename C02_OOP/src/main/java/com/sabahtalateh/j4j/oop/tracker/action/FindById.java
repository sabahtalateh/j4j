package com.sabahtalateh.j4j.oop.tracker.action;

import com.sabahtalateh.j4j.oop.tracker.Item;
import com.sabahtalateh.j4j.oop.tracker.Tracker;
import com.sabahtalateh.j4j.oop.tracker.io.IO;

/**
 * FindById.
 */
public class FindById implements Action {
    /**
     * @param tracker with tasks.
     * @param io      to interact.
     */
    @Override
    public void execute(Tracker tracker, IO io) {
        String id = io.ask("Enter id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            ItemsPrinter itemsPrinter = new ItemsPrinter();
            itemsPrinter.printSingle(item, io);
        } else {
            io.answer("Cannot find item with such ID.");
        }
        io.answer(System.getProperty("line.separator"));
    }
}
