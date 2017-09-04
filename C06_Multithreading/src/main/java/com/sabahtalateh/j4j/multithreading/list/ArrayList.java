package com.sabahtalateh.j4j.multithreading.list;

import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;

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
     */
    @Override
    public void add(E el) {
        if (size == capacity) {
            if (capacity >= MAX_CAPACITY / 2) {
                capacity = MAX_CAPACITY;
            } else {
                capacity = capacity * 2;
            }
            elements = Arrays.copyOf(elements, capacity);
        }
        elements[size++] = el;
    }

    /**
     * @param index index.
     * @return element.
     */
    @Override
    public E get(int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return (E) elements[index];
    }

    /**
     * @return is empty.
     */
    @Override
    public boolean isEmpty() {
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
}
