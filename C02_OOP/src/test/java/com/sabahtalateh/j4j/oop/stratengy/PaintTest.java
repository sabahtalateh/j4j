package com.sabahtalateh.j4j.oop.stratengy;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests.
 */
public class PaintTest {
    @Test
    public void drawTriangle() throws Exception {
        Paint paint = new Paint();
        String triangle = "  *  " + System.getProperty("line.separator")
                        + " *** " + System.getProperty("line.separator")
                        + "*****";

        assertThat(paint.draw(new Triangle()), is(triangle));
    }

    @Test
    public void drawSquare() throws Exception {
        Paint paint = new Paint();
        String square =   "***" + System.getProperty("line.separator")
                        + "***" + System.getProperty("line.separator")
                        + "***";

        assertThat(paint.draw(new Square()), is(square));
    }
}