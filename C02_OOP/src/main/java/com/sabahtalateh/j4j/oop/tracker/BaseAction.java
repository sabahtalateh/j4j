package com.sabahtalateh.j4j.oop.tracker;

/**
 * BaseAction.
 */
public abstract class BaseAction implements Action {

    private int key;

    private String name;

    /**
     * @param key  of action.
     * @param name of action.
     */
    public BaseAction(int key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * @return action info.
     */
    @Override
    public String actionInfo() {
        return String.format("[%d] %s", this.key, this.name);
    }
}
