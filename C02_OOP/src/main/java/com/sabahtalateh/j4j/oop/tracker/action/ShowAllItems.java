package com.sabahtalateh.j4j.oop.tracker.action;

import com.sabahtalateh.j4j.oop.tracker.Tracker;
import com.sabahtalateh.j4j.oop.tracker.io.IO;

/**
 * ShowAllItems.
 */
public class ShowAllItems implements Action {
    /**
     * @param tracker with tasks.
     * @param io      to interact.
     */
    @Override
    public void execute(Tracker tracker, IO io) {
        ItemsPrinter itemsPrinter = new ItemsPrinter();
        itemsPrinter.printList(tracker.getItems(), io);
    }
}
