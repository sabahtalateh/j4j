package com.sabahtalateh.jenkov_tutorials.reflection.class_loading;

/**
 * MyObject.
 */
public class MyObject2 extends MySuperObject implements MyInterface {

    /**
     * Print `Hello reloaded`.
     */
    @Override
    public void hello() {
        System.out.println("Hello reloaded");
    }

}
