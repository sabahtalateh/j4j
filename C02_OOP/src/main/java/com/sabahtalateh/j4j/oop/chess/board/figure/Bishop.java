package com.sabahtalateh.j4j.oop.chess.board.figure;

import com.sabahtalateh.j4j.oop.chess.board.Board;
import com.sabahtalateh.j4j.oop.chess.board.Cell;
import com.sabahtalateh.j4j.oop.chess.board.NoSuchCellException;

/**
 * Bishop.
 */
public class Bishop extends Figure {
    /**
     * @param x coordinate.
     * @param y coordinate.
     */
    public Bishop(int x, int y) {
        super(x, y);
    }

    /**
     * @param position check if can go.
     * @return result.
     * @throws CanNotGoException if can not go.
     */
    @Override
    public boolean canGoTo(Cell position) throws CanNotGoException {
        if (this.getPosition().getY() - position.getX() != this.getPosition().getY() - position.getY()) {
            this.setCanNotGoReason("Bishop can go only diagonally");
            return false;
        }

        return true;
    }

    /**
     * Noting.
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
        Cell from = this.getPosition();

        int distance = Math.abs(from.getX() - to.getX());

        Cell[] throughCells;
        throughCells = new Cell[distance - 1];

        if (from.getX() < to.getX()) {
            for (int i = 0; i < distance - 1; i++) {
                throughCells[i] = board.getCell(from.getX() + i + 1, from.getY() + i + 1);
            }
        } else {
            for (int i = 0; i < distance - 1; i++) {
                throughCells[i] = board.getCell(from.getX() - i - 1, from.getY() - i - 1);
            }
        }

        return throughCells;
    }
}
