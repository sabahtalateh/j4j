package com.sabahtalateh.j4j.oop.tracker.action;

import com.sabahtalateh.j4j.oop.tracker.Tracker;
import com.sabahtalateh.j4j.oop.tracker.io.IO;

/**
 * FindByName.
 */
public class FindByName implements Action {
    /**
     * @param tracker with tasks.
     * @param io      to interact.
     */
    @Override
    public void execute(Tracker tracker, IO io) {
        String name = io.ask("Enter name: ");
        ItemsPrinter itemsPrinter = new ItemsPrinter();
        itemsPrinter.printList(tracker.findByName(name), io);
    }
}
