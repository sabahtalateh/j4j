package com.sabahtalateh.j4j.oop.tracker;

import com.sabahtalateh.j4j.oop.tracker.io.IO;

/**
 * Action.
 */
public interface Action {

    /**
     * @return action key.
     */
    int key();

    /**
     * @return action name.
     */
    String name();

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
