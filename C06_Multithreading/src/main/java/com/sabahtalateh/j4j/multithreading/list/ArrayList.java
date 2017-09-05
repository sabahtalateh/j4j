package com.sabahtalateh.j4j.multithreading.list;

import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;

import static java.lang.Math.max;

/**
 * @param <E> Element type.
 */
@ThreadSafe
public class ArrayList<E> implements List<E> {

    private static final int INITIAL_CAPACITY = 2;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE;
    private int capacity = INITIAL_CAPACITY;
    private int size = 0;

    private Object[] elements = new Object[INITIAL_CAPACITY];

    /**
     * @param el element.
     * @return result.
     */
    @Override
    public synchronized boolean add(E el) {
        boolean added = false;

        if (size != MAX_CAPACITY) {
            increaseCapacityIfRequired();
            elements[size++] = el;
            added = true;
        }

        return added;
    }

    /**
     * @param index index.
     * @param el    element.
     * @return result.
     */
    @Override
    public synchronized boolean add(int index, E el) {
        boolean added = false;

        if (size != MAX_CAPACITY) {
            if (index > capacity) {
                increaseCapacityTo(index + 1);
            }

            if (elements[index] != null) {
                increaseCapacityIfRequired();
                for (int i = size - 1; i >= index; i--) {
                    elements[i + 1] = elements[i];
                }
            }
            elements[index] = el;
            size = max(size + 1, index + 1);
            added = true;
        }

        return added;
    }

    /**
     * @param index index.
     * @return element.
     */
    @Override
    public synchronized E get(int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return (E) elements[index];
    }

    /**
     * @return is empty.
     */
    @Override
    public synchronized boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return size.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @param el element.
     * @return result.
     */
    @Override
    public synchronized boolean contains(E el) {
        boolean contains = false;

        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(el)) {
                contains = true;
                break;
            }
        }

        return contains;
    }

    /**
     * @param index index.
     */
    @Override
    public synchronized void remove(int index) {
        if (index <= size - 1) {
            System.arraycopy(elements, index + 1, elements, index, (size--) - index);
        }
    }

    /**
     * @return string.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int i = 0;
        for (; i < size - 1; i++) {
            sb.append(elements[i]).append(",");
        }
        sb.append(elements[i]);
        sb.append("]");
        return sb.toString();
    }

    /**
     * Increase capacity if required.
     */
    private synchronized void increaseCapacityIfRequired() {
        if (size == capacity) {
            if (capacity >= MAX_CAPACITY / 2) {
                capacity = MAX_CAPACITY;
            } else {
                capacity = capacity * 2;
            }
            elements = Arrays.copyOf(elements, capacity);
        }
    }

    /**
     * @param newCapacity new capacity.
     */
    private synchronized void increaseCapacityTo(int newCapacity) {
        capacity = newCapacity;
        elements = Arrays.copyOf(elements, capacity);
    }
}
