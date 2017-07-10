package com.sabahtalateh.j4j.oop.tracker;

import com.sabahtalateh.j4j.oop.tracker.io.ConsoleIO;
import com.sabahtalateh.j4j.oop.tracker.io.IO;

/**
 * StartUI.
 */
public class StartUI {
    /**
     * Tracker menu.
     */
    private TrackerMenu trackerMenu;

    /**
     * @param io IO system.
     * @param tracker tracker.
     */
    StartUI(IO io, Tracker tracker) {
        this.trackerMenu = new TrackerMenu(io, tracker);
    }

    /**
     * Run UI.
     */
    void run() {
        trackerMenu.startInteraction();
    }

    /**
     * @param args of method.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleIO(), new Tracker()).run();
    }
}
