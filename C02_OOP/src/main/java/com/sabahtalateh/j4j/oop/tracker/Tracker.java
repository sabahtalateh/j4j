package com.sabahtalateh.j4j.oop.tracker;

import java.util.Arrays;

/**
 * Tracker.
 */
public class Tracker {
    /**
     * Default max items in tracker.
     */
    private static final int DEFAULT_MAX_ITEMS = 100;

    /**
     * Tracker items.
     */
    private Item[] items;

    /**
     * Total items.
     */
    private int total = 0;

    /**
     * Max items in tracker.
     */
    private int maxItems;

    /**
     * Item ID generator.
     */
    private final ItemSequence sequence = new ItemSequence();

    /**
     * @param maxItems of tracker.
     */
    public Tracker(int maxItems) {
        this.items = new Item[maxItems];
        this.maxItems = maxItems;
    }

    /**
     * Default constructor.
     */
    public Tracker() {
        this(DEFAULT_MAX_ITEMS);
    }

    /**
     * @param item to add.
     */
    public void add(Item item) {
        item = new Item(item);
        item.setId(sequence.next());
        if (total < this.maxItems) {
            items[total++] = item;
        } else {
            Item[] items = new Item[this.maxItems];
            System.arraycopy(this.items, 1, items, 0, this.maxItems - 1);
            items[this.maxItems - 1] = item;
            this.items = items;
        }
    }

    /**
     * @param item to update.
     */
    public void update(Item item) {
        int itemIndex = this.getItemIndex(item);

        if (itemIndex != -1) {
            this.items[itemIndex] = item;
        }
    }

    /**
     * @param item to delete.
     */
    public void delete(Item item) {
        int itemIndex = this.getItemIndex(item);

        if (itemIndex != -1) {
            for (int i = 0; i < total - 1; i++) {
                Item tmp = this.items[i];
                this.items[i] = this.items[i + 1];
                this.items[i + 1] = tmp;
            }
            total--;
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
    public Item findByName(String name) {
        for (Item item : this.getItems()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }

    /**
     * @return all items.
     */
    public Item[] getItems() {
        return Arrays.copyOfRange(items, 0, total);
    }

    /**
     * @param item to find.
     * @return item's index or -1 if it is not exists.
     */
    private int getItemIndex(Item item) {
        for (int i = 0; i < this.getItems().length; i++) {
            if (this.getItems()[i].getId().equals(item.getId())) {
                return i;
            }
        }
        return -1;
    }
}
