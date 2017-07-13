package com.sabahtalateh.j4j.oop.chess.board.figure;

import com.sabahtalateh.j4j.oop.chess.board.Board;
import com.sabahtalateh.j4j.oop.chess.board.Cell;
import com.sabahtalateh.j4j.oop.chess.board.NoSuchCellException;

import java.util.ArrayList;

/**
 * Knight.
 */
public class Knight extends Figure {
    /**
     * @param x coordinate.
     * @param y coordinate.
     */
    public Knight(int x, int y) {
        super(x, y);
    }

    /**
     * @param position check if can go.
     * @return result.
     * @throws CanNotGoException if can not go.
     */
    @Override
    public boolean canGoTo(Cell position) throws CanNotGoException {
        ArrayList<Cell> availableCells = new ArrayList<>();
        int[] verticals = new int[]{-2, 2};
        int[] horizontals = new int[]{-1, 1};

        for (int vertical : verticals) {
            for (int horizontal : horizontals) {
                availableCells.add(new Cell(this.getPosition().getX() + vertical, this.getPosition().getY() + horizontal));
            }
        }

        boolean canGoTo = availableCells.stream().anyMatch(cell -> cell.equals(position));
        if (!canGoTo) {
            this.setCanNotGoReason("Knight can only go L");
            return false;
        }

        return true;
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
        return new Cell[0];
    }
}
