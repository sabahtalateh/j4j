package com.sabahtalateh.j4j.oop.tracker;

import com.sabahtalateh.j4j.oop.tracker.action.*;
import com.sabahtalateh.j4j.oop.tracker.io.ConsoleIO;
import com.sabahtalateh.j4j.oop.tracker.io.IO;

/**
 * StartUI.
 */
public class StartUI {
    /**
     * Load stubs.
     */
    private static final int MENU_LOAD_STUB_ITEMS = 0;
    /**
     * Add item.
     */
    private static final int MENU_ADD_ITEM = 1;
    /**
     * Edit item.
     */
    private static final int MENU_EDIT_ITEM = 2;
    /**
     * List all items.
     */
    private static final int MENU_LIST_ITEMS = 3;
    /**
     * Find Item by id.
     */
    private static final int MENU_FIND_BY_ID = 4;
    /**
     * Find Item by name.
     */
    private static final int MENU_FIND_BY_NAME = 5;
    /**
     * Delete Item by id.
     */
    private static final int MENU_DELETE_ITEM = 6;
    /**
     * Exit program.
     */
    private static final int MENU_EXIT = 7;

    /**
     * Menu items.
     */
    private String[] menu = new String[8];

    /**
     * Input/Output system.
     */
    private IO io;

    /**
     * Items tracker.
     */
    private final Tracker tracker = new Tracker();

    /**
     * @param io IO system.
     */
    public StartUI(IO io) {
        this.io = io;
        this.menu[MENU_LOAD_STUB_ITEMS] = "Load stub tasks";
        this.menu[MENU_ADD_ITEM] = "Add item";
        this.menu[MENU_EDIT_ITEM] = "Edit item";
        this.menu[MENU_LIST_ITEMS] = "Show all items";
        this.menu[MENU_FIND_BY_ID] = "Find item by id";
        this.menu[MENU_FIND_BY_NAME] = "Find item by name";
        this.menu[MENU_DELETE_ITEM] = "Delete item";
        this.menu[MENU_EXIT] = "Exit";
    }

    /**
     * Run UI.
     */
    private void run() {
        while (true) {
            printMenu();
            Action action = this.createAction(Integer.valueOf(io.ask("Select: ")));
            if (action != null) {
                action.execute(tracker, io);
            } else {
                System.out.println("Action is invalid. Change another.");
            }
        }
    }

    /**
     * @param actionCode to create.
     * @return Action.
     */
    private Action createAction(int actionCode) {
        Action action;
        switch (actionCode) {
            case (MENU_LOAD_STUB_ITEMS):
                action = new LoadStubItems();
                break;
            case (MENU_ADD_ITEM):
                action = new AddItem();
                break;
            case (MENU_EDIT_ITEM):
                action = new EditItem();
                break;
            case (MENU_LIST_ITEMS):
                action = new ShowAllItems();
                break;
            case (MENU_FIND_BY_ID):
                action = new FindById();
                break;
            case (MENU_FIND_BY_NAME):
                action = new FindByName();
                break;
            case (MENU_DELETE_ITEM):
                action = new DeleteItem();
                break;
            case (MENU_EXIT):
                action = new Exit();
                break;
            default:
                action = null;
        }
        return action;
    }

    /**
     * Print menu.
     */
    private void printMenu() {
        System.out.println();
        for (int i = 0; i < this.menu.length; i++) {
            System.out.printf("[%d] %s %n", i, menu[i]);
        }
    }

    /**
     * @param args of method.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleIO()).run();
    }
}
