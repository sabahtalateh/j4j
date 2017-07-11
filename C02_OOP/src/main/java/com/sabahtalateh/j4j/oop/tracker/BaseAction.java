package com.sabahtalateh.j4j.oop.tracker;

/**
 * BaseAction.
 */
public abstract class BaseAction implements Action {
    /**
     * @return action info.
     */
    @Override
    public String actionInfo() {
        return String.format("[%d] %s", this.key(), this.name());
    }
}
