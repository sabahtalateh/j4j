package com.sabahtalateh.j4j.collections_advanced.list;

import java.util.Iterator;

/**
 * @param <E> Type.
 */
public class LinkedContainer<E> implements Container<E>, Iterator<E> {

    private Node<E> head = null;

    private Node<E> tail = null;

    private int size = 0;

    private int iteratorIndex = 0;

    private Node<E> iteratorCurrent = null;

    /**
     * @param e entity.
     */
    public void add(E e) {
        Node<E> node;
        if (this.head == null && this.tail == null) {
            node = new Node<>(null, e, null);
            this.head = node;
            this.tail = node;
        } else {
            node = new Node<>(tail, e, null);
            this.tail.setNext(node);
            this.tail = node;
        }
        this.size++;
    }

    /**
     * @param index index.
     * @return element.
     * @throws NoSuchIndexException no such index.
     */
    @Override
    public E get(int index) {
        if (index >= size) {
            throw new NoSuchIndexException("No such index in list.");
        }

        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.value;
    }

    /**
     * @return has next.
     */
    @Override
    public boolean hasNext() {
        return iteratorIndex < size;
    }

    /**
     * @return next.
     */
    @Override
    public E next() {
        E next = null;

        if (this.hasNext()) {
            if (this.iteratorCurrent == null) {
                next = this.head.value;
                this.iteratorCurrent = this.head;
            } else {
                next = iteratorCurrent.value;
            }
            this.iteratorCurrent = this.iteratorCurrent.next;
            this.iteratorIndex++;
        }

        return next;
    }

    /**
     * @param <E> Type.
     */
    private static class Node<E> {
        E value;
        Node<E> next;
        Node<E> prev;

        /**
         * @param prev  previous.
         * @param value value.
         * @param next  next.
         */
        Node(Node<E> prev, E value, Node<E> next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        /**
         * @param next next element.
         */
        void setNext(Node<E> next) {
            this.next = next;
        }
    }
}

