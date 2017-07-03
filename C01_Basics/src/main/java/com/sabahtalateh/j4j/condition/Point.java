package com.sabahtalateh.j4j.condition;

/**
 * Point.
 */
public class Point {
    /**
     * X coordinate.
     */
    private int x;

    /**
     * Y coordinate.
     */
    private int y;

    /**
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @return Y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Check if point belongs to function equation.
     * Function equation defines as `y = a * x + b`.
     *
     * Example: 2 * x + 1. a = 2, b = 1.
     *
     * @param a coefficient
     * @param b coefficient
     * @return checking result
     */
    public boolean belongsToFunction(int a, int b) {
        return this.y == a * this.x + b;
    }

    /**
     * @return string.
     */
    @Override
    public String toString() {
        return "Point{x=" + x + ", y=" + y + "}";
    }
}
