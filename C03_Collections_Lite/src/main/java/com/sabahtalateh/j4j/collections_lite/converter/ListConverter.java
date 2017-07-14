package com.sabahtalateh.j4j.collections_lite.converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ListConverter.
 */
public class ListConverter {
    /**
     * @param array to converter.
     * @return list.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();

        for (int[] row : array) {
            for (int element : row) {
                list.add(element);
            }
        }
        return list;
    }

    /**
     * @param list to convert.
     * @param size of result array.
     * @return array.
     */
    public int[][] toArray(List<Integer> list, int size) {
        Iterator<Integer> iterator = list.iterator();

        int[][] array = new int[size][size];

        int row = 0;
        int col = 0;
        while (iterator.hasNext()) {
            if (col == size) {
                row++;
                col = 0;
            } else {
                array[row][col++] = iterator.next();
            }
        }

        return array;
    }
}
