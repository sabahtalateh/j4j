package com.sabahtalateh.j4j.collections_advanced.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @param <T> Type.
 */
public class SimpleArray<T> {
    List<T> list = new ArrayList<>();

    /**
     * @param element element.
     */
    public void add(T element) {
        this.list.add(element);
    }

    /**
     * @param index index.
     * @return element.
     */
    public T get(int index) {
        return this.list.get(index);
    }

    /**
     * @param element element.
     */
    public void delete(T element) {
        this.list.remove(element);
    }

    /**
     * @param index   index.
     * @param element element.
     */
    public void update(int index, T element) {
        this.list.remove(index);
        this.list.add(index, element);
    }

    /**
     * @return size.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * @return stream.
     */
    public Stream<T> stream() {
        return list.stream();
    }
}
