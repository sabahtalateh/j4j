package com.sabahtalateh.j4j.collections_lite.organizations;

import java.util.ArrayList;
import java.util.List;

/**
 * Division.
 */
public class Division {
    private String code;
    private List<Division> subdivisions = new ArrayList<>();

    /**
     * @param code code.
     */
    public Division(String code) {
        this.code = code;
    }

    /**
     * @param subdivision subdivision.
     */
    public void addSubdivision(Division subdivision) {
        this.subdivisions.add(subdivision);
    }

    /**
     * @return subdivision.
     */
    public List<Division> getSubdivisions() {
        return this.subdivisions;
    }

    /**
     * @return code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @param o object.
     * @return result.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Division division = (Division) o;

        return code != null ? code.equals(division.code) : division.code == null;
    }

    /**
     * @return hash code.
     */
    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }

    /**
     * @return string.
     */
    @Override
    public String toString() {
        return "{" + code + "," + subdivisions + '}';
    }
}
