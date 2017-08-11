package com.sabahtalateh.j4j.collections_advanced.list;

/**
 * @param <E> Type.
 */
interface Container<E> {

    /**
     * @param e element to add.
     */
    void add(E e);

    /**
     * @param index index.
     * @return element.
     */
    E get(int index);
}
