package com.sabahtalateh.j4j.collections_advanced.list;

/**
 * @param <T> Type.
 */
public class Node<T> {
    private T value;
    private Node<T> next;

    /**
     * @param value value.
     */
    Node(T value) {
        this.value = value;
    }

    /**
     * @param next next.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * @return next.
     */
    public Node<T> getNext() {
        return next;
    }
}
