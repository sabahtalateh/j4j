package com.sabahtalateh.j4j.oop.chess.board.figure;

import com.sabahtalateh.j4j.oop.chess.board.Board;
import com.sabahtalateh.j4j.oop.chess.board.Cell;
import com.sabahtalateh.j4j.oop.chess.board.NoSuchCellException;

import java.util.ArrayList;

/**
 * Knight.
 */
public class Knight extends Figure {

    public Knight(Cell position) {
        super(position);
    }

    public Knight(int x, int y) {
        super(x, y);
    }

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

    @Override
    public void afterGoRoutines() {
    }

    @Override
    public Cell[] getPathThroughCells(Cell to, Board board) throws NoSuchCellException {
        return new Cell[0];
    }
}
