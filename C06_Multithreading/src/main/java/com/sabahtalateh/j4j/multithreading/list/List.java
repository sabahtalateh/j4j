package com.sabahtalateh.j4j.multithreading.list;

/**
 * @param <E> Element type.
 */
public interface List<E> {
    /**
     * @param el element.
     * @return result.
     */
    boolean add(E el);

    /**
     * @param index index.
     * @param el    element.
     * @return result.
     */
    boolean add(int index, E el);

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

    /**
     * @param el element.
     * @return result.
     */
    boolean contains(E el);

    /**
     * @param index index.
     * @return removed.
     */
    boolean remove(int index);
}
