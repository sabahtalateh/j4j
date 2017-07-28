package com.sabahtalateh.j4j.collections_advanced.list;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @param <E> type.
 */
public class ArrayContainer<E> implements Container<E>, Iterator<E> {

    private static final int MAX_CAPACITY = Integer.MAX_VALUE;

    private static final int DEFAULT_CAPACITY = 100;

    private int capacity;

    private int lastIndex = 0;

    private Object[] elements;

    private int iteratorIndex = 0;

    /**
     * @param capacity container capacity.
     */
    public ArrayContainer(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Container capacity should be positive.");
        }
        this.capacity = capacity;
        this.elements = new Object[this.capacity];
    }

    /**
     * Default constructor.
     */
    public ArrayContainer() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * @param e element to add.
     */
    @Override
    public void add(E e) {
        if (this.lastIndex < MAX_CAPACITY) {
            if (!this.enoughCapacity()) {
                this.increaseCapacity();
            }
            this.elements[lastIndex++] = e;
        }
    }

    /**
     * @param index index.
     * @return element.
     */
    @Override
    public E get(int index) {
        return (E) this.elements[index];
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
     * @return has next.
     */
    @Override
    public boolean hasNext() {
        return iteratorIndex < lastIndex;
    }

    /**
     * @return next.
     */
    @Override
    public E next() {
        return (E) this.elements[iteratorIndex++];
    }
}
