package com.sabahtalateh.j4j.oop.tracker.action;

import com.sabahtalateh.j4j.oop.tracker.Tracker;
import com.sabahtalateh.j4j.oop.tracker.io.IO;

/**
 * Action.
 */
public interface Action {
    /**
     * @param tracker with tasks.
     * @param io to interact.
     */
    void execute(Tracker tracker, IO io);
}
