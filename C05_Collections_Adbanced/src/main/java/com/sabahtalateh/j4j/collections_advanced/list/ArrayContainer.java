package com.sabahtalateh.j4j.collections_advanced.list;

import java.util.Arrays;

/**
 * @param <E> type.
 */
public class ArrayContainer<E> implements Container<E> {

    private static final int DEFAULT_CAPACITY = 100;

    private int size;

    private int position = 0;

    private Object[] elements = new Object[size];

    /**
     * @param size container size.
     */
    public ArrayContainer(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Container size should be positive.");
        }
        this.size = size;
    }

    /**
     * Default constructor.
     */
    public ArrayContainer() {
        this.size = DEFAULT_CAPACITY;
    }

    /**
     * @param e element to add.
     */
    @Override
    public void add(E e) {
        if (!this.enoughCapacity()) {
            this.increaseCapacity();
        }
        this.elements[position++] = e;
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
        return size < elements.length;
    }

    /**
     * Increase capacity.
     */
    private void increaseCapacity() {
        this.size *= 2;
        this.elements = Arrays.copyOf(this.elements, this.size);
    }
}
