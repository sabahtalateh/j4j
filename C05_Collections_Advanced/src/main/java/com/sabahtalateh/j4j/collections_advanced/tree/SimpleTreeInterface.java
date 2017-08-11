package com.sabahtalateh.j4j.collections_advanced.tree;

/**
 * @param <E> Elements type.
 */
interface SimpleTreeInterface<E> {

    /**
     * @param parent parent node.
     * @param child  child node.
     * @return true if added, false if not.
     */
    boolean add(E parent, E child);
}
