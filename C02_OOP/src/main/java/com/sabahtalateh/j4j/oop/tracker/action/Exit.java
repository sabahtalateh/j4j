package com.sabahtalateh.j4j.oop.tracker.action;

import com.sabahtalateh.j4j.oop.tracker.Tracker;
import com.sabahtalateh.j4j.oop.tracker.io.IO;

/**
 * Exit.
 */
public class Exit implements Action {
    /**
     * @param tracker with tasks.
     * @param io      to interact.
     */
    @Override
    public void execute(Tracker tracker, IO io) {
        io.answer("Bue..");
        System.exit(0);
    }
}
