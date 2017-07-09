package com.sabahtalateh.j4j.oop.stratengy;

/**
 * Square.
 */
public class Square implements Shape {
    /**
     * @return square.
     */
    @Override
    public String picture() {
        return    "***" + System.getProperty("line.separator")
                + "***" + System.getProperty("line.separator")
                + "***";
    }
}
