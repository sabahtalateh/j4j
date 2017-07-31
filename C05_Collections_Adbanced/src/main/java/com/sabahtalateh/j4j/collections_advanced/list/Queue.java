package com.sabahtalateh.j4j.collections_advanced.list;

/**
 * @param <E> Type.
 */
public class Queue<E> extends LinkedContainer<E> {

    /**
     * @param e element.
     */
    public void enqueue(E e) {
        this.add(e);
    }

    /**
     * @return value.
     */
    public E dequeue() {
        E value = null;
        if (this.size > 0) {
            value = this.head.value;
            this.head = this.head.next;
            this.size--;
        }
        return value;
    }
}
