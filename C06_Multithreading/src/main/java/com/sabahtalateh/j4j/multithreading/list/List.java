package com.sabahtalateh.j4j.multithreading.list;

/**
 * @param <E> Element type.
 */
public interface List<E> {
    /**
     * @param el element.
     */
    void add(E el);

    /**
     * @param index index.
     * @return element.
     */
    E get(int index);

    /**
     * @return is empty.
     */
    boolean isEmpty();

    /**
     * @return size.
     */
    int size();
}
