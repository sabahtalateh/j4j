package com.sabahtalateh.jenkov_tutorials.reflection.proxy;

/**
 * FooImpl.
 */
public class FooImpl implements FooInterface {
    /**
     * {@inheritDoc}
     */
    @Override
    public String addExclamation(String string) {
        return string + "!";
    }
}
