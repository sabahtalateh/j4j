package com.sabahtalateh.j4j.condition;

/**
 * Triangle class.
 */
public class Triangle {
    /**
     * Point.
     */
    private Point a;

    /**
     * Point.
     */
    private Point b;

    /**
     * Point.
     */
    private Point c;

    /**
     * @param a point.
     * @param b point.
     * @param c point.
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * @return calculate triangle area.
     */
    public double area() {
        double abLength = Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
        double bcLength = Math.sqrt(Math.pow(c.getX() - b.getX(), 2) + Math.pow(c.getY() - b.getY(), 2));
        double caLength = Math.sqrt(Math.pow(a.getX() - c.getX(), 2) + Math.pow(a.getY() - c.getY(), 2));

        if (
                abLength + bcLength < caLength
                || bcLength + caLength < abLength
                || caLength + abLength < bcLength
        ) {
            return Double.NaN;
        }

        double halfPerimeter = (abLength + bcLength + caLength) / 2;

        return Math.sqrt(halfPerimeter * (halfPerimeter - abLength) * (halfPerimeter - bcLength) * (halfPerimeter - caLength));
    }
}
