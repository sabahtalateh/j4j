package com.sabahtalateh.j4j.collections_advanced.list;

import java.util.ArrayList;
import java.util.List;

/**
 * NodeCycleDetector.
 */
class NodeCycleDetector {
    /**
     * @param node start node.
     * @return result.
     */
    boolean hasCycles(Node node) {
        boolean result = false;
        List<Node> nodeList = new ArrayList<>();
        while (node.getNext() != null) {
            if (nodeList.contains(node.getNext())) {
                result = true;
                break;
            }
            nodeList.add(node.getNext());
            node = node.getNext();
        }
        return result;
    }
}
