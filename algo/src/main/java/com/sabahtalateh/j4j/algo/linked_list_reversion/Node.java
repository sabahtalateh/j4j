package com.sabahtalateh.j4j.algo.linked_list_reversion;

/**
 * @param <T> type.
 */
public class Node<T> {
    private T value;
    private Node<T> next;

    /**
     * @param value value.
     */
    public Node(T value) {
        this.value = value;
    }

    /**
     * @param value value.
     * @param next  node.
     */
    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    /**
     * @param next node.
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

    /**
     * @return has next.
     */
    public boolean hasNext() {
        return this.next != null;
    }

    /**
     * @param o object.
     * @return result.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        if (value != null ? !value.equals(node.value) : node.value != null) return false;
        return next != null ? next.equals(node.next) : node.next == null;
    }

    /**
     * @return hash code.
     */
    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (next != null ? next.hashCode() : 0);
        return result;
    }

    /**
     * @return string.
     */
    @Override
    public String toString() {
        String string = String.valueOf(value);

        if (this.hasNext()) {
            string += "->" + next;
        }

        return string;
    }
}
