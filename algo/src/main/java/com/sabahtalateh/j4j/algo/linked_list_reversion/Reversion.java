package com.sabahtalateh.j4j.algo.linked_list_reversion;

/**
 * Reversion.
 */
public class Reversion {
    /**
     * @param node node.
     * @param <T>  type.
     * @return reverted.
     */
    public static <T> Node<T> revert(Node<T> node) {

        Node<T> curr = node;
        Node<T> next = null;
        Node<T> prev = null;

        while (curr != null) {
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
