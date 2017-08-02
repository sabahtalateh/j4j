package com.sabahtalateh.j4j.collections_advanced.set;

import java.util.Iterator;

/**
 * @param <E> Type.
 */
public class LinkedSet<E> implements Set<E> {

    private Node<E> head = null;

    private Node<E> tail = null;

    private int size = 0;

    /**
     * @param e element.
     */
    @Override
    public void add(E e) {
        if (!this.contains(e)) {
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
    }

    /**
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node<E> currentNode = null;

            int index = 0;

            /**
             * @return has next.
             */
            @Override
            public boolean hasNext() {
                return this.index < LinkedSet.this.size;
            }

            /**
             * @return next.
             */
            @Override
            public E next() {
                E next = null;
                if (this.hasNext()) {
                    if (this.currentNode == null) {
                        next = LinkedSet.this.head.value;
                        this.currentNode = LinkedSet.this.head;
                    } else {
                        next = currentNode.value;
                    }
                    this.currentNode = this.currentNode.next;
                    this.index++;
                }

                return next;
            }
        };
    }

    /**
     * @param <E> Type.
     */
    static class Node<E> {
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

    /**
     * @param e element.
     * @return true if contains, false if not.
     */
    private boolean contains(E e) {
        boolean contains = false;
        Node<E> node = this.head;

        for (int i = 0; i < this.size; i++) {
            if (node.value.equals(e)) {
                contains = true;
                break;
            }
            node = node.next;
        }

        return contains;
    }
}
