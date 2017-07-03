package com.sabahtalateh.j4j.arrays;

/**
 * RotateMatrix.
 */
public class RotateMatrix {
    /**
     * @param matrix to rotate.
     * @return rotated matrix.
     */
    public int[][] rotate(int[][] matrix) {
        int length = matrix.length;
        int tmp;
        for (int i = 0; i < length / 2; i++) {
            for (int j = i; j < length - 1 - i; j++) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[length - j - 1][i];
                matrix[length - j - 1][i] = matrix[length - i - 1][length - j - 1];
                matrix[length - i - 1][length - j - 1] = matrix[j][length - i - 1];
                matrix[j][length - i - 1] = tmp;
            }
        }

        return matrix;
    }
}
