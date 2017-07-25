package com.sabahtalateh.j4j.collections_lite.organizations;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * DivisionCollection.
 */
public class DivisionCollection {
    private List<Division> divisions = new ArrayList<>();

    /**
     * @param divisionCode division to add.
     */
    public void add(String divisionCode) {
        String[] codes = divisionCode.split("\\\\");
        if (codes.length == 0) {
            return;
        }

        Division root = new Division(codes[0]);

        if (!divisions.contains(root)) {
            divisions.add(root);
        } else {
            root = divisions.get(divisions.indexOf(root));
        }

        for (int i = 1; i < codes.length; i++) {
            Division child = new Division(codes[i]);
            if (!root.getSubdivisions().contains(child)) {
                root.addSubdivision(child);
            }
            root = root.getSubdivisions().get(root.getSubdivisions().indexOf(child));
        }
    }

    /**
     * @return divisions.
     */
    public List<Division> getDivisions() {
        return this.divisions;
    }

    /**
     * @return division names.
     */
    public List<String> getDivisionNames() {
        List<String> nodePaths = new ArrayList<>();
        for (Division root : this.divisions) {
            this.fillDivisionNames(root, root.getSubdivisions(), new Stack<>(), nodePaths);
        }
        return nodePaths;
    }

    /**
     * @return get division names in ascending order.
     */
    public List<String> getDivisionNamesAscending() {
        List<String> divisions = this.getDivisionNames();
        divisions.sort(String::compareTo);
        return divisions;
    }

    /**
     * @return get division names in descending order.
     */
    public List<String> getDivisionNamesDescending() {
        List<String> divisions = this.getDivisionNames();
        divisions.sort((o1, o2) -> {
            int len1 = o1.length();
            int len2 = o2.length();
            int lim = Math.min(len1, len2);
            char[] v1 = o1.toCharArray();
            char[] v2 = o2.toCharArray();

            int k = 0;
            while (k < lim) {
                char c1 = v1[k];
                char c2 = v2[k];
                if (c1 != c2) {
                    return c2 - c1;
                }
                k++;
            }
            return len1 - len2;

        });
        return divisions;
    }

    /**
     * @param parent     parent node.
     * @param children   children.
     * @param pathToNode path to parent node.
     * @param paths      this variable will be filled with generated paths.
     */
    private void fillDivisionNames(Division parent, List<Division> children, Stack<Division> pathToNode, List<String> paths) {
        String nodeFullPath = pathToNode.stream().map(Division::getCode).collect(Collectors.joining("\\"));
        nodeFullPath = pathToNode.isEmpty() ? parent.getCode() : format("%s\\%s", nodeFullPath, parent.getCode());
        paths.add(nodeFullPath);

        pathToNode.push(parent);
        if (!parent.getSubdivisions().isEmpty()) {
            for (Division div : children) {
                fillDivisionNames(div, div.getSubdivisions(), pathToNode, paths);
            }
        }
        pathToNode.pop();
    }
}
