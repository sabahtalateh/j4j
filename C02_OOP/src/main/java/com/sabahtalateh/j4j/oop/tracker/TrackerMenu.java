package com.sabahtalateh.j4j.oop.tracker;

import com.sabahtalateh.j4j.oop.tracker.io.IO;

import java.util.ArrayList;
import java.util.List;

/**
 * TrackerMenu.
 */
class TrackerMenu {

    private IO io;

    private Tracker tracker;

    private final List<Action> actions = new ArrayList<>();

    private List<Integer> actionKeys = new ArrayList<>();

    /**
     * @param io      IO system.
     * @param tracker with items.
     */
    TrackerMenu(IO io, Tracker tracker) {
        this.io = io;
        this.tracker = tracker;
        this.fillMenuItems();
        this.fillActionKeys();
    }

    /**
     * Fill menu action keys.
     */
    private void fillActionKeys() {
        for (int i = 0; i < this.actions.size(); i++) {
            actionKeys.add(i, i);
        }
    }

    /**
     * Fill menu with actions.
     */
    private void fillMenuItems() {
        int position = 0;
        this.actions.add(position++, new TrackerMenu.LoadStubs(0, "Load stubs"));
        this.actions.add(position++, this.new AddItem(1, "Add item"));
        this.actions.add(position++, new EditItem(2, "Edit item"));
        this.actions.add(position++, new ShowAll(3, "Show all"));
        this.actions.add(position++, new FindById(4, "Find by ID"));
        this.actions.add(position++, new FindByName(5, "Find by name"));
        this.actions.add(position++, new DeleteItem(6, "Delete item"));
        this.actions.add(position, new Exit(7, "Exit"));
    }

    /**
     * Print menu to IO system.
     */
    private void presentMenu() {
        for (Action action : actions) {
            io.answer(action.actionInfo());
        }
    }

    /**
     * Run all the process.
     */
    void startInteraction() {
        Action action = null;
        while (!(action instanceof Exit)) {
            presentMenu();
            int actionKey = io.ask("Select action: ", this.actionKeys);
            action = actions.get(actionKey);
            action.execute(tracker, io);
        }
    }

    // Actions

    /**
     * LoadStubs.
     */
    private static class LoadStubs extends BaseAction {
        /**
         * @param key  of action.
         * @param name of action.
         */
        LoadStubs(int key, String name) {
            super(key, name);
        }

        /**
         * @param tracker with tasks.
         * @param io      to interact.
         */
        @Override
        public void execute(Tracker tracker, IO io) {
            int amount = Math.abs(Integer.valueOf(io.ask("How much? ")));

            for (int i = 0; i < amount; i++) {
                Item item = new Item("Task " + (i + 1), "Description " + (i + 1));
                tracker.add(item);
            }
            io.answer(String.format("%s Stub tasks loaded..", amount));
        }
    }

    /**
     * AddItem.
     */
    private class AddItem extends BaseAction {
        /**
         * @param key  of action.
         * @param name of action.
         */
        AddItem(int key, String name) {
            super(key, name);
        }

        /**
         * @param tracker with tasks.
         * @param io      to interact.
         */
        @Override
        public void execute(Tracker tracker, IO io) {
            Item item = new Item(io.ask("[Name]: "), io.ask("[Description]: "));
            tracker.add(item);
            io.answer(String.format("Item [%s] added.", item.getName()));
        }
    }
}

/**
 * EditItem.
 */
class EditItem extends BaseAction {
    /**
     * @param key  of action.
     * @param name of action.
     */
    EditItem(int key, String name) {
        super(key, name);
    }

    /**
     * @param tracker with tasks.
     * @param io      to interact.
     */
    @Override
    public void execute(Tracker tracker, IO io) {
        String id = io.ask("Enter [ID] of item to edit: ");
        Item item = tracker.findById(id);

        if (item == null) {
            io.answer("There is no such item in tracker.");
            return;
        }

        item.setName(io.ask("New [Name]: "));
        item.setDescription(io.ask("New [Description]: "));
        tracker.update(item);
        io.answer(String.format("Item [%s] updated.", item.getId()));
    }
}

/**
 * ShowAll.
 */
class ShowAll extends BaseAction {
    /**
     * @param key  of action.
     * @param name of action.
     */
    ShowAll(int key, String name) {
        super(key, name);
    }

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

/**
 * FindById.
 */
class FindById extends BaseAction {
    /**
     * @param key  of action.
     * @param name of action.
     */
    FindById(int key, String name) {
        super(key, name);
    }

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

/**
 * FindByName.
 */
class FindByName extends BaseAction {
    /**
     * @param key  of action.
     * @param name of action.
     */
    FindByName(int key, String name) {
        super(key, name);
    }

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

/**
 * DeleteItem.
 */
class DeleteItem extends BaseAction {
    /**
     * @param key  of action.
     * @param name of action.
     */
    DeleteItem(int key, String name) {
        super(key, name);
    }

    /**
     * @param tracker with tasks.
     * @param io      to interact.
     */
    @Override
    public void execute(Tracker tracker, IO io) {
        String id = io.ask("Type id of item to delete: ");
        Item item = tracker.findById(id);
        if (item != null) {
            tracker.delete(item);
            io.answer("Deleted.");
        } else {
            io.answer("No such item.");
        }
    }
}

/**
 * Exit.
 */
class Exit extends BaseAction {
    /**
     * @param key  of action.
     * @param name of action.
     */
    Exit(int key, String name) {
        super(key, name);
    }

    /**
     * @param tracker with tasks.
     * @param io      to interact.
     */
    @Override
    public void execute(Tracker tracker, IO io) {
        io.answer("Bue..");
    }
}
