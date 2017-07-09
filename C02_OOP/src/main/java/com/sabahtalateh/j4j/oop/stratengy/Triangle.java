package com.sabahtalateh.j4j.oop.stratengy;

/**
 * Triangle.
 */
public class Triangle implements Shape {
    /**
     * @return triangle as string.
     */
    @Override
    public String picture() {
        return    "  *  " + System.getProperty("line.separator")
                + " *** " + System.getProperty("line.separator")
                + "*****";
    }
}
