package com.sabahtalateh.j4j.loop;

/**
 * Pyramid.
 */
public class Pyramid {
    /**
     * @param height board height.
     * @return board.
     */
    public String paint(int height) {
        return paint(height, "^", " ");
    }

    /**
     * @param height      board height.
     * @param fillSymbol  symbol to fill not empty cells.
     * @param emptySymbol symbol to fill empty cells.
     * @return board.
     */
    public String paint(int height, String fillSymbol, String emptySymbol) {
        StringBuilder stringBuilder = new StringBuilder();
        String lineSeparator = System.getProperty("line.separator");

        int width = 2 * (height - 1) + 1;

        for (int i = 0; i < height; i++) {
            int fillNumber = 2 * i + 1;
            int emptyNumber = (width - fillNumber) / 2;
            // fill left empty space.
            stringBuilder.append(this.repeatSymbol(emptySymbol, emptyNumber));
            // fill not empty space.
            stringBuilder.append(this.repeatSymbol(fillSymbol, fillNumber));
            // fill right empty space.
            stringBuilder.append(this.repeatSymbol(emptySymbol, emptyNumber));
            if (i != height - 1) {
                stringBuilder.append(lineSeparator);
            }
        }

        return stringBuilder.toString();
    }

    /**
     * @param symbol to repeat.
     * @param times repeat.
     * @return repeated.
     */
    private String repeatSymbol(String symbol, int times) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            stringBuilder.append(symbol);
        }
        return stringBuilder.toString();
    }
}
