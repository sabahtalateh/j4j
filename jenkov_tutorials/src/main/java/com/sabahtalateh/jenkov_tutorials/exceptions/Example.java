package com.sabahtalateh.jenkov_tutorials.exceptions;

/**
 * Example.
 */
public class Example {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        new Example().run();
    }

    /**
     * Run.
     */
    public void run() {
        this.m1();
    }

    /**
     * m1.
     */
    public void m1() {
        try {
            this.m2();
        } catch (RuntimeException e) {
            throw new RuntimeException("Exception from m1", e);
        }

    }

    /**
     * m2.
     */
    public void m2() {
        throw new RuntimeException("Exception from 2");
    }
}
