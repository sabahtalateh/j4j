package com.sabahtalateh.j4j.oop.chess.board;

/**
 * Cell.
 */
public class Cell {
    private final int x;
    private final int y;

    /**
     * @param x coordinate.
     * @param y coordinate.
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return X.
     */
    public int getX() {
        return x;
    }

    /**
     * @return Y.
     */
    public int getY() {
        return y;
    }

    /**
     * @return String.
     */
    @Override
    public String toString() {
        return "Cell{x=" + x + ", y=" + y + '}';
    }

    /**
     * @param o to check.
     * @return result.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cell cell = (Cell) o;

        return x == cell.x && y == cell.y;
    }

    /**
     * @return hash code.
     */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
