package com.sabahtalateh.j4j.collections_advanced.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @param <E> Element type.
 */
public class SimpleTree<E extends Comparable<E>> implements SimpleTreeInterface<E> {

    private Node root;

    /**
     * @param root root.
     */
    SimpleTree(E root) {
        this.root = this.new Node(root);
    }

    /**
     * @param parent parent node.
     * @param child  child node.
     * @return true if added, false if not.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean inserted = false;
        Optional<Node> nodeToInsert = root.findChild(new Node(parent));

        if (parent.compareTo(child) != 0 && nodeToInsert.isPresent()) {
            inserted = true;
            nodeToInsert.get().appendChild(new Node(child));
        }

        return inserted;
    }

    /**
     * @return iterator.
     */
    public Iterator<E> iterator() {
        return this.asList(this.root, new ArrayList<>()).iterator();
    }

    /**
     * @return check if node is binary tree.
     */
    public boolean isBinary() {
        return this.root.isBinary();
    }

    /**
     * Node.
     */
    private class Node {
        List<Node> children = new ArrayList<>();
        E value;

        /**
         * @param value value.
         */
        Node(E value) {
            this.value = value;
        }

        /**
         * @param child child to append.
         */
        void appendChild(Node child) {
            this.children.add(child);
        }

        /**
         * @param node node to find.
         * @return node.
         */
        Optional<Node> findChild(Node node) {
            Optional<Node> result = Optional.empty();

            if (this.value.compareTo(node.value) == 0) {
                return Optional.of(this);
            }

            if (!this.children.isEmpty()) {
                for (Node child : this.children) {
                    result = child.findChild(node);
                    if (result.isPresent()) {
                        return result;
                    }
                }
            }

            return result;
        }

        /**
         * @return check if node is binary tree.
         */
        boolean isBinary() {
            if (this.children.size() > 2) {
                return false;
            }

            for (Node child : this.children) {
                if (!child.isBinary()) {
                    return false;
                }
            }

            return true;
        }
    }

    /**
     * @param node node from where to start.
     * @param list already filled nodes list.
     * @return node as list.
     */
    private List<E> asList(Node node, List<E> list) {
        list.add(node.value);
        if (!node.children.isEmpty()) {
            for (Node child : node.children) {
                this.asList(child, list);
            }
        }

        return list;
    }
}
