package com.sabahtalateh.j4j.collections_lite.convert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ListConverter.
 */
public class ListConverter {
    /**
     * @param array to convert.
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

    public static void main(String[] args) {
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);

        new ListConverter().toArray(expected, 3);
    }
}
