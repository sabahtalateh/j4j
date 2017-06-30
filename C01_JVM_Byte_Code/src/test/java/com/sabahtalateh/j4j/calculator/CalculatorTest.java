package com.sabahtalateh.j4j.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests.
 */
public class CalculatorTest {

    /**
     * Threshold.
     */
    private final double epsilon = 1e-7;

    @Test
    public void addingTest() {
        Calculator calculator = new Calculator();

        calculator.add(1D, 1D);
        assertEquals(calculator.getResult(), 2D, epsilon);

        calculator = new Calculator();
        calculator.add(3D, 2.5);
        assertEquals(calculator.getResult(), 5.5, epsilon);
    }

    @Test
    public void subtractionTest() {
        Calculator calculator = new Calculator();

        calculator.subtract(4.5, 2.2);
        assertEquals(calculator.getResult(), 2.3, epsilon);
    }

    @Test
    public void multiplicationTest() {
        Calculator calculator = new Calculator();

        calculator.multiply(9999999999D, 0);
        assertEquals(calculator.getResult(), 0, epsilon);

        calculator.multiply(12.3, -14.1);
        assertEquals(calculator.getResult(), -173.43, epsilon);

        calculator.multiply(13, 11);
        assertEquals(calculator.getResult(), 143, epsilon);
    }

    @Test
    public void divisionTest() {
        Calculator calculator = new Calculator();

        calculator.divide(4, 0);
        assertEquals(calculator.getResult(), Double.POSITIVE_INFINITY, epsilon);

        calculator.divide(1.5, 2);
        assertEquals(calculator.getResult(), 0.75, epsilon);
    }
}
