package com.sabahtalateh.j4j.oop.chess.board.figure;

import com.sabahtalateh.j4j.oop.chess.board.Board;
import com.sabahtalateh.j4j.oop.chess.board.Cell;
import com.sabahtalateh.j4j.oop.chess.board.NoSuchCellException;

/**
 * Castle.
 */
public class Castle extends Figure {
    public Castle(Cell position) {
        super(position);
    }

    public Castle(int x, int y) {
        super(x, y);
    }

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

    @Override
    public void afterGoRoutines() {
    }

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
