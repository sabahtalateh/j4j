package com.sabahtalateh.j4j.collections_advanced.generics;

/**
 * @param <T> Type.
 */
interface Store<T extends Base> {

    /**
     * @param element element.
     */
    void add(T element);

    /**
     * @param element element.
     */
    void delete(T element);

    /**
     * @param element element.
     */
    void update(T element);

    /**
     * @param id id.
     * @return element.
     */
    T findById(String id);
}
