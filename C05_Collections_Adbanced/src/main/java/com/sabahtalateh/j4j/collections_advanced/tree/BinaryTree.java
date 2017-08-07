package com.sabahtalateh.j4j.collections_advanced.tree;

/**
 * @param <E> Type.
 */
public class BinaryTree<E extends Comparable<E>> {

    private Node root;

    /**
     * @param e value.
     */
    void add(E e) {
        if (this.root == null) {
            this.root = new Node(e);
        } else {
            this.root.insert(e);
        }
    }

    /**
     * @param e value.
     * @return result.
     */
    boolean contains(E e) {
        return this.root.contains(e);
    }

    /**
     * Node.
     */
    private class Node {
        E value;
        Node left;
        Node right;

        /**
         * @param value value.
         */
        Node(E value) {
            this.value = value;
        }

        /**
         * @param value value to insert.
         */
        void insert(E value) {
            Node target;
            if (this.value.compareTo(value) > 0) {
                target = this.left == null ? this.left = new Node(null) : this.left;
            } else {
                target = this.right == null ? this.right = new Node(null) : this.right;
            }

            if (target.value == null) {
                target.setValue(value);
            } else {
                target.insert(value);
            }
        }

        /**
         * @param value value.
         * @return result.
         */
        boolean contains(E value) {
            int comparisonResult = this.value.compareTo(value);
            if (comparisonResult == 0) {
                return true;
            }

            Node target = comparisonResult > 0 ? this.left : this.right;
            return target == null ? false : target.contains(value);
        }

        /**
         * @param value value.
         */
        void setValue(E value) {
            this.value = value;
        }
    }

}
