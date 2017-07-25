package com.sabahtalateh.j4j.collections_lite.organizations;

import java.util.ArrayList;
import java.util.List;

/**
 * Division.
 */
public class Division {
    String code;
    List<Division> subdivisions = new ArrayList<>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Division division = (Division) o;

        return code != null ? code.equals(division.code) : division.code == null;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }

    public boolean equalsByCode(Division division) {
        return this.code.equals(division.code);
    }
}
