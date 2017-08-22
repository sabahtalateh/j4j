package com.sabahtalateh.j4j.multithreading.oracle.immutable;

/**
 * ImmutableRGB.
 */
public class ImmutableRGB {
    private final int red;
    private final int green;
    private final int blue;
    private final String name;

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
    public ImmutableRGB(int red, int green, int blue, String name) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
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
     * @return immutable.
     */
    public synchronized ImmutableRGB invert() {
        return new ImmutableRGB(255 - red, 255 - green, 255 - blue, "Inverse of " + name);
    }
}
