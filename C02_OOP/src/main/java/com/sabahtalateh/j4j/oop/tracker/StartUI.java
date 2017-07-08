package com.sabahtalateh.j4j.oop.tracker;

import com.sabahtalateh.j4j.oop.tracker.action.*;
import com.sabahtalateh.j4j.oop.tracker.io.ConsoleIO;
import com.sabahtalateh.j4j.oop.tracker.io.IO;

/**
 * StartUI.
 */
public class StartUI {
    /**
     * Menu items.
     */
    private String[] menu = new String[Menu.values().length];

    /**
     * Input/Output system.
     */
    private IO io;

    /**
     * Items tracker.
     */
    private final Tracker tracker;

    /**
     * @param io IO system.
     * @param tracker tracker.
     */
    StartUI(IO io, Tracker tracker) {
        this.io = io;
        this.tracker = tracker;
    }

    /**
     * Run UI.
     */
    void run() {
        this.initMenu();
        while (true) {
            printMenu();
            Action action = this.createAction(Integer.valueOf(io.ask("Select: ")));
            if (action != null) {
                action.execute(tracker, io);
                if (action instanceof Exit) {
                    return;
                }
            } else {
                this.io.answer("Action is invalid. Change another.");
            }
        }
    }

    /**
     * @param actionCode to create.
     * @return Action.
     */
    private Action createAction(int actionCode) {
        Action action = null;
        if (actionCode == Menu.LOAD_STUBS.ordinal()) {
            action = new LoadStubItems();
        } else if (actionCode == Menu.ADD_ITEM.ordinal()) {
            action = new AddItem();
        } else if (actionCode == Menu.EDIT_ITEM.ordinal()) {
            action = new EditItem();
        } else if (actionCode == Menu.SHOW_ALL.ordinal()) {
            action = new ShowAllItems();
        } else if (actionCode == Menu.FIND_BY_NAME.ordinal()) {
            action = new FindByName();
        } else if (actionCode == Menu.FIND_BY_ID.ordinal()) {
            action = new FindById();
        } else if (actionCode == Menu.DELETE_ITEM.ordinal()) {
            action = new DeleteItem();
        } else if (actionCode == Menu.EXIT.ordinal()) {
            action = new Exit();
        }
        return action;
    }

    /**
     * Initialize menu.
     */
    private void initMenu() {
        this.menu[Menu.LOAD_STUBS.ordinal()] = "Load stub tasks";
        this.menu[Menu.ADD_ITEM.ordinal()] = "Add item";
        this.menu[Menu.EDIT_ITEM.ordinal()] = "Edit item";
        this.menu[Menu.SHOW_ALL.ordinal()] = "Show all items";
        this.menu[Menu.FIND_BY_ID.ordinal()] = "Find item by id";
        this.menu[Menu.FIND_BY_NAME.ordinal()] = "Find item by name";
        this.menu[Menu.DELETE_ITEM.ordinal()] = "Delete item";
        this.menu[Menu.EXIT.ordinal()] = "Exit";
    }

    /**
     * Print menu.
     */
    private void printMenu() {
        for (int i = 0; i < this.menu.length; i++) {
            this.io.answer(String.format("[%d] %s", i, menu[i]));
        }
    }

    /**
     * @param args of method.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleIO(), new Tracker()).run();
    }
}
