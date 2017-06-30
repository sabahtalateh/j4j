package com.sabahtalateh.j4j.calculator;

/**
 * You can use to calculate something.
 * Yeah. Awesome.
 */
public class Calculator {
    /**
     * Calculation result.
     */
    private double result;

    /**
     * @param a first arg
     * @param b second arg
     */
    public void add(double a, double b) {
        this.result = a + b;
    }

    /**
     * @param a first arg
     * @param b second arg
     */
    public void subtract(double a, double b) {
        this.result = a - b;
    }

    /**
     * @param a first arg
     * @param b second arg
     */
    public void multiply(double a, double b) {
        this.result = a * b;
    }

    /**
     * @param a first arg
     * @param b second arg
     */
    public void divide(double a, double b) {
        this.result = a / b;
    }

    /**
     * @return calculation result
     */
    public double getResult() {
        return result;
    }
}
