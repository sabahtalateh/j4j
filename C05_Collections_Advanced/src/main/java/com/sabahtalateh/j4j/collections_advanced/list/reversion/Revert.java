package com.sabahtalateh.j4j.collections_advanced.list.reversion;

/**
 * Revert.
 */
public class Revert {
    /**
     * @param n1 node.
     * @param <T> Node type param.
     * @return reverted.
     */
    public <T> Node<T> revert(Node<T> n1) {
        if (n1.hasNext()) {
            Node<T> n2 = n1.getNext();
            if (!n2.hasNext()) {
                n2.appendNext(n1);
                n1.clearNext();
                n1 = n2;
            } else {
                Node<T> n3 = n2.getNext();
                n2.appendNext(n1);
                n1.clearNext();

                while (true) {
                    n1 = n2;
                    n2 = n3;
                    if (n2 == null) {
                        break;
                    }
                    n3 = n2.getNext();
                    n2.appendNext(n1);
                }
            }
        }
        return n1;
    }
}
