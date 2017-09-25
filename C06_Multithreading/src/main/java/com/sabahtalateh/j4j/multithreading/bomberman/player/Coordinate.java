package com.sabahtalateh.j4j.multithreading.bomberman.player;

/**
 * Coordinate.
 */
public class Coordinate {
    private int x;
    private int y;

    /**
     * @param x x.
     * @param y y.
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return x.
     */
    public int getX() {
        return x;
    }

    /**
     * @return y.
     */
    public int getY() {
        return y;
    }

    /**
     * @return string.
     */
    @Override
    public String toString() {
        return "Coordinate{x=" + x + ", y=" + y + '}';
    }
}
