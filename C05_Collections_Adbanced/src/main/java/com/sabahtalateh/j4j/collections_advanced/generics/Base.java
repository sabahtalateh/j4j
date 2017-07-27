package com.sabahtalateh.j4j.collections_advanced.generics;

/**
 * Base.
 */
public abstract class Base {

    private String id;

    /**
     * @param id id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return id.
     */
    public String getId() {
        return id;
    }

    /**
     * @param name name.
     */
    public abstract void setName(String name);

    /**
     * @return name.
     */
    public abstract String getName();
}
