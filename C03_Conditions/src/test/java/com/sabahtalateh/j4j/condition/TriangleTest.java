package com.sabahtalateh.j4j.condition;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

/**
 * Test triangle.
 */
public class TriangleTest {
    /**
     * double precision calculation error.
     */
    private double e = 1e-7;

    @Test
    public void test1by1TriangleArea() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Point p3 = new Point(1, 0);
        Triangle t1 = new Triangle(p1, p2, p3);

        assertThat(t1.area(), closeTo(0.5, e));
    }

    @Test
    public void testBigTriangleArea() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 10);
        Point p3 = new Point(19, 26);
        Triangle t1 = new Triangle(p1, p2, p3);

        assertThat(t1.area(), closeTo(43, e));
    }

    @Test
    public void testZeroTriangleArea() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        Point p3 = new Point(0, 0);
        Triangle t1 = new Triangle(p1, p2, p3);

        assertThat(t1.area(), closeTo(0, e));
    }

    @Test
    public void testLineTriangleArea() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 10);
        Point p3 = new Point(0, 5);
        Triangle t1 = new Triangle(p1, p2, p3);

        assertThat(t1.area(), closeTo(0, e));
    }
}