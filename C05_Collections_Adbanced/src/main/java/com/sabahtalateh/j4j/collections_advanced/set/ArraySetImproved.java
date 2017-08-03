package com.sabahtalateh.j4j.collections_advanced.set;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @param <E> Type.
 */
public class ArraySetImproved<E> implements Set<E> {

    private static final int MAX_CAPACITY = Integer.MAX_VALUE;

    private static final int DEFAULT_CAPACITY = 100;

    private int capacity;

    private int lastIndex = 0;

    private Object[] elements;

    /**
     * @param capacity container capacity.
     */
    public ArraySetImproved(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Container capacity should be positive.");
        }
        this.capacity = capacity;
        this.elements = new Object[this.capacity];
    }

    /**
     * Default constructor.
     */
    public ArraySetImproved() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * @param e element.
     */
    @Override
    public void add(E e) {
        if (this.lastIndex < MAX_CAPACITY && !this.contains(e)) {
            if (!this.enoughCapacity()) {
                this.increaseCapacity();
            }
            int hash = e.hashCode();
            this.elements[this.getIndexToInsert(hash)] = new Entry<>(hash, e);
            this.lastIndex++;
        }
    }

    private int getIndexToInsert(int hash) {
        if (this.lastIndex < 6) {
            return lastIndex;
        }

        int result = 0;
        int rangeStart = 0;
        int rangeEnd = this.lastIndex;
        int middle = 0;

        if (lastIndex != 0) {
            while (rangeEnd - rangeStart != 1) {
                middle = (rangeStart + rangeEnd) / 2;
                if (hash > ((Entry<E>) this.elements[middle]).hash) {
                    rangeStart = middle;
                } else {
                    rangeEnd = middle;
                }
            }

        }

        return middle;
    }

    /**
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < ArraySetImproved.this.lastIndex;
            }

            @Override
            public E next() {
                return ((Entry<E>) ArraySetImproved.this.elements[this.index++]).element;
            }
        };
    }

    /**
     * @param e element.
     * @return true if contains, false if not.
     */
    private boolean contains(E e) {
        boolean result = false;
        for (int i = 0; i < this.lastIndex; i++) {
            if (this.elements[i].equals(e)) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * @return check if capacity is enough.
     */
    private boolean enoughCapacity() {
        return capacity > this.lastIndex;
    }

    /**
     * Increase capacity.
     */
    private void increaseCapacity() {
        if (this.capacity <= 0) {
            this.capacity = DEFAULT_CAPACITY;
        } else {
            if (this.capacity <= MAX_CAPACITY / 2) {
                this.capacity *= 2;
            } else {
                this.capacity = MAX_CAPACITY;
            }
        }

        this.elements = Arrays.copyOf(this.elements, this.capacity);
    }

    private static class Entry<E> {
        int hash;
        E element;

        public Entry(int hash, E element) {
            this.hash = hash;
            this.element = element;
        }
    }
}
