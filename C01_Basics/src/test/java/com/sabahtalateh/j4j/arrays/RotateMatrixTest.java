package com.sabahtalateh.j4j.arrays;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 */
public class RotateMatrixTest {
    @Test
    public void rotate2X2MatrixTest() {
        RotateMatrix rotateMatrix = new RotateMatrix();

        int[][] matrix = {
                {1, 2},
                {3, 4}
        };

        int[][] expected = {
                {3, 1},
                {4, 2}
        };

        assertThat(rotateMatrix.rotate(matrix), is(expected));
    }


    @Test
    public void rotate3X3MatrixTest() {
        RotateMatrix rotateMatrix = new RotateMatrix();

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] expected = {
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3},
        };

        assertThat(rotateMatrix.rotate(matrix), is(expected));
    }
}
