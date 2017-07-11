package com.sabahtalateh.j4j.oop.tracker;

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

    /**
     * @return action description.
     */
    String actionInfo();
}
