package com.sabahtalateh.j4j.collections_advanced.list;

/**
 * @param <E> Type.
 */
public class Stack<E> extends LinkedContainer<E> {

    /**
     * @param e element.
     */
    public void push(E e) {
        this.add(e);
    }

    /**
     * @return value.
     */
    public E pop() {
        E value = null;
        if (this.size > 0) {
            value = this.tail.value;
            this.tail = this.tail.prev;
            this.size--;
        }
        return value;
    }
}
