package com.sabahtalateh.j4j.collections_advanced.set;

import java.util.Iterator;

/**
 * @param <E> Type.
 */
interface Set<E> {
    /**
     * @param e element.
     */
    void add(E e);

    /**
     * @return iterator.
     */
    Iterator<E> iterator();
}
