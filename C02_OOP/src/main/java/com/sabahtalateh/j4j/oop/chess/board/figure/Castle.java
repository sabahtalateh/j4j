package com.sabahtalateh.j4j.oop.chess.board.figure;

import com.sabahtalateh.j4j.oop.chess.board.Board;
import com.sabahtalateh.j4j.oop.chess.board.Cell;
import com.sabahtalateh.j4j.oop.chess.board.NoSuchCellException;

/**
 * Castle.
 */
public class Castle extends Figure {
    /**
     * @param x coordinate.
     * @param y coordinate.
     */
    public Castle(int x, int y) {
        super(x, y);
    }

    /**
     * @param position check if can go.
     * @return result.
     * @throws CanNotGoException if can not go.
     */
    @Override
    public boolean canGoTo(Cell position) throws CanNotGoException {
        boolean result = false;
        if (this.getPosition().getX() == position.getX() || this.getPosition().getY() == position.getY()) {
            result = true;
        } else {
            this.setCanNotGoReason("Castle can only go vertically or horizontally.");
        }

        return result;
    }

    /**
     * Nothing.
     */
    @Override
    public void afterGoRoutines() {
    }

    /**
     * @param to    go.
     * @param board board.
     * @return cells.
     * @throws NoSuchCellException if no cells.
     */
    @Override
    public Cell[] getPathThroughCells(Cell to, Board board) throws NoSuchCellException {
        Cell[] throughCells;

        if (this.getPosition().getX() == to.getX()) {
            int greaterCoordinate = Math.max(this.getPosition().getY(), to.getY());
            int lessCoordinate = Math.min(this.getPosition().getY(), to.getY());
            throughCells = new Cell[greaterCoordinate - lessCoordinate - 1];
            int count = 0;
            for (int i = lessCoordinate + 1; i < greaterCoordinate; i++) {
                throughCells[count++] = board.getCell(to.getX(), i);
            }
        } else {
            int greaterCoordinate = Math.max(this.getPosition().getX(), to.getX());
            int lessCoordinate = Math.min(this.getPosition().getX(), to.getX());
            throughCells = new Cell[greaterCoordinate - lessCoordinate - 1];
            int count = 0;
            for (int i = lessCoordinate + 1; i < greaterCoordinate; i++) {
                throughCells[count++] = board.getCell(i, to.getY());
            }
        }

        return throughCells;
    }
}
