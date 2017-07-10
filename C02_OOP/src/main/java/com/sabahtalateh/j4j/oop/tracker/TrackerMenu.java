package com.sabahtalateh.j4j.oop.tracker;

import com.sabahtalateh.j4j.oop.tracker.io.IO;

/**
 * TrackerMenu.
 */
class TrackerMenu {

    private IO io;

    private Tracker tracker;

    private final Action[] actions = new Action[8];

    /**
     * @param io IO system.
     * @param tracker with items.
     */
    TrackerMenu(IO io, Tracker tracker) {
        this.io = io;
        this.tracker = tracker;
        this.fillMenuItems();
    }

    /**
     * Fill menu with actions.
     */
    private void fillMenuItems() {
        Action loadStubs = new TrackerMenu.LoadStubs();
        actions[loadStubs.key()] = loadStubs;

        Action addItem = this.new AddItem();
        actions[addItem.key()] = addItem;

        Action editItem = new EditItem();
        actions[editItem.key()] = editItem;

        Action showAll = new ShowAll();
        actions[showAll.key()] = showAll;

        Action findById = new FindById();
        actions[findById.key()] = findById;

        Action findByName = new FindByName();
        actions[findByName.key()] = findByName;

        Action deleteItem = new DeleteItem();
        actions[deleteItem.key()] = deleteItem;

        Action exit = new Exit();
        actions[exit.key()] = exit;
    }

    /**
     * Print menu to IO system.
     */
    private void presentMenu() {
        for (Action action : actions) {
            io.answer(String.format("[%s] %s", action.key(), action.actionInfo()));
        }
    }

    /**
     * Run all the process.
     */
    void startInteraction() {
        Action action = null;
        while (!(action instanceof Exit)) {
            presentMenu();
            int actionKey = Integer.valueOf(io.ask("Select action: "));
            action = actions[actionKey];
            action.execute(tracker, io);
        }
    }

    // Actions
    /**
     * LoadStubs.
     */
    private static class LoadStubs implements Action {
        /**
         * @return action key.
         */
        @Override
        public int key() {
            return 0;
        }

        /**
         * @param tracker with tasks.
         * @param io to interact.
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

        /**
         * @return action description.
         */
        @Override
        public String actionInfo() {
            return "Load stub items";
        }
    }

    /**
     * AddItem.
     */
    private class AddItem implements Action {
        /**
         * @return action key.
         */
        @Override
        public int key() {
            return 1;
        }

        /**
         * @param tracker with tasks.
         * @param io to interact.
         */
        @Override
        public void execute(Tracker tracker, IO io) {
            Item item = new Item(io.ask("[Name]: "), io.ask("[Description]: "));
            tracker.add(item);
            io.answer(String.format("Item [%s] added.", item.getName()));
        }

        /**
         * @return action description.
         */
        @Override
        public String actionInfo() {
            return "Add item";
        }

    }
}

/**
 * EditItem.
 */
class EditItem implements Action {
    /**
     * @return action key.
     */
    @Override
    public int key() {
        return 2;
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

    /**
     * @return action description.
     */
    @Override
    public String actionInfo() {
        return "Edit item";
    }
}

/**
 * ShowAll.
 */
class ShowAll implements Action {
    /**
     * @return action key.
     */
    @Override
    public int key() {
        return 3;
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

    /**
     * @return action description.
     */
    @Override
    public String actionInfo() {
        return "Show all items";
    }
}

/**
 * FindById.
 */
class FindById implements Action {
    /**
     * @return action key.
     */
    @Override
    public int key() {
        return 4;
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

    /**
     * @return action description.
     */
    @Override
    public String actionInfo() {
        return "Find by ID";
    }
}

/**
 * FindByName.
 */
class FindByName implements Action {
    /**
     * @return action key.
     */
    @Override
    public int key() {
        return 5;
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

    /**
     * @return action description.
     */
    @Override
    public String actionInfo() {
        return "Find by name";
    }
}

/**
 * DeleteItem.
 */
class DeleteItem implements Action {
    /**
     * @return action key.
     */
    @Override
    public int key() {
        return 6;
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

    /**
     * @return action description.
     */
    @Override
    public String actionInfo() {
        return "Delete item";
    }
}

/**
 * Exit.
 */
class Exit implements Action {
    /**
     * @return action key.
     */
    @Override
    public int key() {
        return 7;
    }

    /**
     * @param tracker with tasks.
     * @param io      to interact.
     */
    @Override
    public void execute(Tracker tracker, IO io) {
        io.answer("Bue..");
    }

    /**
     * @return action description.
     */
    @Override
    public String actionInfo() {
        return "Exit";
    }
}
