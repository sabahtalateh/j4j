package com.sabahtalateh.j4j.loop;

/**
 * Board.
 */
public class Board {

    /**
     * @param width  board width.
     * @param height board height.
     * @return board.
     */
    public String paint(int width, int height) {
        return paint(width, height, "X", " ");
    }

    /**
     * @param width       board width.
     * @param height      board height.
     * @param fillSymbol  symbol to fill not empty cells.
     * @param emptySymbol symbol to fill empty cells.
     * @return board.
     */
    public String paint(int width, int height, String fillSymbol, String emptySymbol) {
        StringBuilder stringBuilder = new StringBuilder();

        boolean prevCellFilled = false;
        String lineSeparator = System.getProperty("line.separator");

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                stringBuilder.append(prevCellFilled ? emptySymbol : fillSymbol);
                prevCellFilled = !prevCellFilled;
            }
            if (i != width - 1) {
                stringBuilder.append(lineSeparator);
            }
        }

        return stringBuilder.toString();
    }
}
