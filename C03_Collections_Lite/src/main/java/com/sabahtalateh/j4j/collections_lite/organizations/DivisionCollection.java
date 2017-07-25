package com.sabahtalateh.j4j.collections_lite.organizations;

import java.util.ArrayList;
import java.util.List;

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
                root.getSubdivisions().add(child);
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
}
