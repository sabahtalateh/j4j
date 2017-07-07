package com.sabahtalateh.j4j.oop.tracker.action;

import com.sabahtalateh.j4j.oop.tracker.Item;
import com.sabahtalateh.j4j.oop.tracker.io.IO;
import org.atteo.evo.inflector.English;

/**
 * ItemsPrinter.
 */
public class ItemsPrinter {
    /**
     * @param items to print.
     * @param io    to interact.
     */
    public void printList(Item[] items, IO io) {
        for (Item item : items) {
            this.printSingle(item, io);
        }

        if (items.length != 0) {
            io.answer(String.format("%s %s printed.", items.length, English.plural("item", items.length)));
        } else {
            io.answer("No items in tracker");
        }
    }

    /**
     * @param item to print.
     * @param io   to interact.
     */
    public void printSingle(Item item, IO io) {
        io.answer(String.format("[ID]           %s", item.getId()));
        io.answer(String.format("[Name]         %s", item.getName()));
        io.answer(String.format("[Description]  %s", item.getDescription()));
        io.answer("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
}
