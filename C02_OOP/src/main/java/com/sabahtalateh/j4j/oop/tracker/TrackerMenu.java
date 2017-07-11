package com.sabahtalateh.j4j.oop.tracker;

import com.sabahtalateh.j4j.oop.tracker.io.IO;

/**
 * TrackerMenu.
 */
class TrackerMenu {

    private IO io;

    private Tracker tracker;

    private final Action[] actions = new Action[8];

    private int[] actionKeys = new int[8];

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
        for (int i = 0; i < actions.length; i++) {
            actionKeys[i] = i;
        }
    }

    /**
     * Fill menu with actions.
     */
    private void fillMenuItems() {
        int position = 0;
        actions[position++] = new TrackerMenu.LoadStubs();
        actions[position++] = this.new AddItem();
        actions[position++] = new EditItem();
        actions[position++] = new ShowAll();
        actions[position++] = new FindById();
        actions[position++] = new FindByName();
        actions[position++] = new DeleteItem();
        actions[position] = new Exit();
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
            action = actions[actionKey];
            action.execute(tracker, io);
        }
    }

    // Actions

    /**
     * LoadStubs.
     */
    private static class LoadStubs extends BaseAction {
        /**
         * @return action key.
         */
        @Override
        public int key() {
            return 0;
        }

        /**
         * @return name.
         */
        @Override
        public String name() {
            return "Load stubs";
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
         * @return action key.
         */
        @Override
        public int key() {
            return 1;
        }

        /**
         * @return name.
         */
        @Override
        public String name() {
            return "Add item";
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
     * @return action key.
     */
    @Override
    public int key() {
        return 2;
    }

    /**
     * @return name.
     */
    @Override
    public String name() {
        return "Edit";
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
     * @return action key.
     */
    @Override
    public int key() {
        return 3;
    }

    /**
     * @return name.
     */
    @Override
    public String name() {
        return "Show all";
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
     * @return action key.
     */
    @Override
    public int key() {
        return 4;
    }

    /**
     * @return name.
     */
    @Override
    public String name() {
        return "Find by ID";
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
     * @return action key.
     */
    @Override
    public int key() {
        return 5;
    }

    /**
     * @return name.
     */
    @Override
    public String name() {
        return "Find by name";
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
     * @return action key.
     */
    @Override
    public int key() {
        return 6;
    }

    /**
     * @return action name.
     */
    @Override
    public String name() {
        return "Delete item";
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
     * @return action key.
     */
    @Override
    public int key() {
        return 7;
    }

    /**
     * @return name.
     */
    @Override
    public String name() {
        return "Exit";
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
