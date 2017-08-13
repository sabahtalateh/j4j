package com.sabahtalateh.j4j.collections_advanced.list.reversion;

/**
 * @param <T> Type.
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
     * @param node node.
     */
    public void appendNext(Node<T> node) {
        this.next = node;
    }

    /**
     * @return result.
     */
    public boolean hasNext() {
        return this.next != null;
    }

    /**
     * @return next.
     */
    public Node<T> getNext() {
        return this.next;
    }

    /**
     * Clears next.
     */
    public void clearNext() {
        this.next = null;
    }

    /**
     * @return string.
     */
    @Override
    public String toString() {
        return "Node{value=" + value + ", next=" + next + '}';
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
}
