package com.sabahtalateh.j4j.oop.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tracker.
 */
public class Tracker {
    /**
     * Tracker items.
     */
    private List<Item> items = new ArrayList<>();

    /**
     * Item ID generator.
     */
    private final ItemSequence sequence = new ItemSequence();

    /**
     * @param item to add.
     */
    public void add(Item item) {
        item = new Item(item);
        item.setId(sequence.next());
        items.add(item);
    }

    /**
     * @param item to update.
     */
    public void update(Item item) {
        int itemIndex = this.getItemIndex(item);

        if (itemIndex != -1) {
            this.items.remove(itemIndex);
            this.items.add(itemIndex, item);
        }
    }

    /**
     * @param item to delete.
     */
    public void delete(Item item) {
        int itemIndex = this.getItemIndex(item);

        if (itemIndex != -1) {
            this.items.remove(itemIndex);
        }
    }

    /**
     * @param id to find.
     * @return Item.
     */
    public Item findById(String id) {
        for (Item item : this.getItems()) {
            if (item.getId().equals(id)) {
                return item;
            }
        }

        return null;
    }

    /**
     * @param name to find.
     * @return Item.
     */
    public List<Item> findByName(String name) {
        return this.items.stream().filter(o -> o.getName().contains(name)).collect(Collectors.toList());
    }

    /**
     * @return all items.
     */
    public List<Item> getItems() {
        return this.items;
    }

    /**
     * @param item to find.
     * @return item's index or -1 if it is not exists.
     */
    private int getItemIndex(Item item) {
        return this.items.indexOf(item);
    }
}
