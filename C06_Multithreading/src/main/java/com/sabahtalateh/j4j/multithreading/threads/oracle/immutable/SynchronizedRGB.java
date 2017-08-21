package com.sabahtalateh.j4j.multithreading.threads.oracle.immutable;

/**
 * SynchronizedRGB.
 */
public class SynchronizedRGB {
    private int red;
    private int green;
    private int blue;
    private String name;

    /**
     * @param red   red.
     * @param green green.
     * @param blue  blue.
     */
    private void check(int red, int green, int blue) {
        if (red < 0 || red > 255
                || green < 0 || green > 255
                || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @param red   red.
     * @param green green.
     * @param blue  blue.
     * @param name  name.
     */
    public SynchronizedRGB(int red, int green, int blue, String name) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    /**
     * @param red   red.
     * @param green green.
     * @param blue  blue.
     * @param name  name.
     */
    public void set(int red, int green, int blue, String name) {
        check(red, green, blue);
        synchronized (this) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
        }
    }

    /**
     * @return color.
     */
    public synchronized int getRGB() {
        return ((red << 16) | (green << 8) | blue);
    }

    /**
     * @return name.
     */
    public synchronized String getName() {
        return name;
    }

    /**
     * Invert.
     */
    public synchronized void invert() {
        red = 255 - red;
        green = 255 - green;
        blue = 255 - blue;
        name = "Inverse of " + name;
    }
}
