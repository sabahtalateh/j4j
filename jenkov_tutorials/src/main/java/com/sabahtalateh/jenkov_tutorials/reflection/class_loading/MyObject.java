package com.sabahtalateh.jenkov_tutorials.reflection.class_loading;

/**
 * MyObject.
 */
public class MyObject extends MySuperObject implements MyInterface {

    /**
     * Print `Hello original`.
     */
    @Override
    public void hello() {
        System.out.println("Hello original");
    }

}
