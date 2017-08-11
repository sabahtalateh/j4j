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
        int hash = e.hashCode();
        int insertPosition = this.getInsertPosition(hash);
        if (insertPosition != -1 && this.lastIndex < MAX_CAPACITY) {
            if (!this.enoughCapacity()) {
                this.increaseCapacity();
            }
            if (insertPosition < this.lastIndex) {
                // Shift elements right from insert position.
                Entry<E> tmp = (Entry<E>) this.elements[insertPosition];
                for (int i = this.lastIndex + 1; i > insertPosition; i--) {
                    this.elements[i + 1] = this.elements[i];
                }
                this.elements[insertPosition + 1] = tmp;
            }
            this.elements[insertPosition] = new Entry<>(hash, e);
            this.lastIndex++;
        }
    }

    /**
     * @param hash hashcode.
     * @return not negative index or -1 if such element is already exists in storage.
     */
    private int getInsertPosition(int hash) {
        int middleIndex = 0;
        if (lastIndex != 0) {
            int rangeStart = 0;
            int rangeEnd = this.lastIndex;

            while (rangeEnd > rangeStart) {
                middleIndex = (rangeStart + rangeEnd) / 2;
                int middleHash = ((Entry<E>) this.elements[middleIndex]).hash;
                if (middleHash == hash) {
                    middleIndex = -1;
                    break;
                } else {
                    if (middleHash < hash) {
                        rangeStart = ++middleIndex;
                    } else {
                        rangeEnd = middleIndex;
                    }
                }
            }
        }

        return middleIndex;
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

    /**
     * @param <E> Type.
     */
    private static class Entry<E> {
        int hash;
        E element;

        /**
         * @param hash    hash.
         * @param element element.
         */
        Entry(int hash, E element) {
            this.hash = hash;
            this.element = element;
        }
    }
}
