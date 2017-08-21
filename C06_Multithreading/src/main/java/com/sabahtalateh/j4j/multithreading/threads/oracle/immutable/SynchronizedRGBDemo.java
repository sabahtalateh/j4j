package com.sabahtalateh.j4j.multithreading.threads.oracle.immutable;

/**
 * SynchronizedRGBDemo.
 */
public class SynchronizedRGBDemo {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        SynchronizedRGB black = new SynchronizedRGB(0, 0, 0, "Black");

        // Not safe.
        int blackRGB = black.getRGB();
        String blackName = black.getName();

        // Safe.
        synchronized (black) {
            blackRGB = black.getRGB();
            blackName = black.getName();
        }
    }
}
