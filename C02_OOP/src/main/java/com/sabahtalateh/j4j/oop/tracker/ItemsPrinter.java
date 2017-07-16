package com.sabahtalateh.j4j.oop.tracker;

import com.sabahtalateh.j4j.oop.tracker.io.IO;
import org.atteo.evo.inflector.English;

import java.util.List;

/**
 * ItemsPrinter.
 */
public class ItemsPrinter {
    /**
     * @param items to print.
     * @param io    to interact.
     */
    public void printList(List<Item> items, IO io) {
        for (Item item : items) {
            this.printSingle(item, io);
        }

        if (!items.isEmpty()) {
            io.answer(String.format("%s %s printed.", items.size(), English.plural("item", items.size())));
        } else {
            io.answer("No items in tracker");
        }
    }

    /**
     * @param item to print.
     * @param io   to interact.
     */
    public void printSingle(Item item, IO io) {
        io.answer(String.format("{ID}           %s", item.getId()));
        io.answer(String.format("{Name}         %s", item.getName()));
        io.answer(String.format("{Description}  %s", item.getDescription()));
        io.answer("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
}
