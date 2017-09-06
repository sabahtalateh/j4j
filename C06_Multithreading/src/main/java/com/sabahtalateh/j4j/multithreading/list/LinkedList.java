package com.sabahtalateh.j4j.multithreading.list;

/**
 * @param <E> type.
 */
public class LinkedList<E> implements List<E> {

    private Node<E> head = null;
    private Node<E> current = null;

    private int size = 0;

    /**
     * @param el element.
     * @return result.
     */
    @Override
    public synchronized boolean add(E el) {
        if (head == null) {
            head = new Node<>(el);
            current = head;
        } else {
            Node<E> next = new Node<>(el);
            current.setNext(next);
            current = next;
        }
        size++;
        return true;
    }

    /**
     * @param index index.
     * @param el    element.
     * @return result.
     */
    @Override
    public synchronized boolean add(int index, E el) {
        boolean added = false;

        if (index <= size) {
            if (index == 0 && head == null) {
                head = new Node<>(el);
                current = head;
            } else {
                Node<E> appendTo = head;
                for (int i = 0; i < index - 1; i++) {
                    appendTo = appendTo.getNext();
                }
                Node<E> appendee = new Node<>(el);
                if (appendTo.hasNext()) {
                    Node<E> nextAfterAppendee = appendTo.getNext();
                    appendee.setNext(nextAfterAppendee);
                    appendTo.setNext(appendee);
                } else {
                    appendTo.setNext(appendee);
                }
            }
            size++;
            added = true;
        }

        return added;
    }

    /**
     * @param index index.
     * @return element.
     */
    @Override
    public synchronized E get(int index) {
        if (index < size) {
            Node<E> node = head;
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
            return node.getValue();
        }
        return null;
    }

    /**
     * @return result.
     */
    @Override
    public synchronized boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return size.
     */
    @Override
    public synchronized int size() {
        return size;
    }

    /**
     * @param el element.
     * @return contains.
     */
    @Override
    public synchronized boolean contains(E el) {
        boolean contains = false;

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (current.getValue().equals(el)) {
                contains = true;
                break;
            }
            current = current.getNext();
        }

        return contains;
    }

    /**
     * @param index index.
     * @return removed.
     */
    @Override
    public synchronized boolean remove(int index) {
        boolean removed = false;

        if (index < size) {
            if (index == 0) {
                if (head.hasNext()) {
                    head = head.getNext();
                } else {
                    head = null;
                }
            } else {
                removed = true;
                Node<E> removeAfter = head;
                for (int i = 0; i < index - 1; i++) {
                    removeAfter = removeAfter.getNext();
                }
                if (removeAfter.getNext().hasNext()) {
                    removeAfter.setNext(removeAfter.getNext().getNext());
                } else {
                    removeAfter.setNext(null);
                }
            }
            size--;
        }

        return removed;
    }

    /**
     * @return string.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node<E> cur = head;

        while (cur.hasNext()) {
            sb.append(cur.value).append(",");
            cur = cur.getNext();
        }
        sb.append(cur.getValue()).append("]");

        return sb.toString();
    }

    /**
     * @param <E> type.
     */
    private static class Node<E> {
        private E value;
        private Node<E> next;

        /**
         * @param value value.
         */
        Node(E value) {
            this.value = value;
        }

        /**
         * @return value.
         */
        public E getValue() {
            return value;
        }

        /**
         * @param next next.
         */
        void setNext(Node<E> next) {
            this.next = next;
        }

        /**
         * @return next.
         */
        Node<E> getNext() {
            return next;
        }

        /**
         * @return has next.
         */
        boolean hasNext() {
            return next != null;
        }

        /**
         * @return string.
         */
        @Override
        public String toString() {
            return "Node{value=" + value + ", next=" + next + '}';
        }
    }
}
