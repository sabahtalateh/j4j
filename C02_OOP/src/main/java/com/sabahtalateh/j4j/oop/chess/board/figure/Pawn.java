package com.sabahtalateh.j4j.oop.chess.board.figure;

import com.sabahtalateh.j4j.oop.chess.board.Board;
import com.sabahtalateh.j4j.oop.chess.board.Cell;
import com.sabahtalateh.j4j.oop.chess.board.NoSuchCellException;

/**
 * Pawn.
 */
public class Pawn extends Figure {

    private boolean firsStep = true;

    public Pawn(Cell position) {
        super(position);
    }

    public Pawn(int x, int y) {
        this(new Cell(x, y));
    }

    @Override
    public boolean canGoTo(Cell position) throws CanNotGoException {
        boolean result = false;

        if (this.position.getY() != position.getY()) {
            this.setCanNotGoReason("Pawn can only goes vertically");
        }

        int distance = position.getX() - this.position.getX();
        if (distance < 1) {
            this.setCanNotGoReason("Pawn can only goes forward");
        }

        if (distance == 2) {
            if (this.firsStep) {
                result = true;
            } else {
                this.setCanNotGoReason("Pawn can goes two cells only at the first step");
            }
        }

        if (distance > 2) {
            this.setCanNotGoReason("Pawn can not go so far");
        }

        return result;
    }


    @Override
    public void afterGoRoutines() {
        this.firsStep = false;
    }

    /**
     * Path without start and end positions.
     *
     * @param to    where to go.
     * @param board chessboard.
     * @return cells.
     */
    @Override
    public Cell[] getPathThroughCells(Cell to, Board board) throws NoSuchCellException {
        int distance = to.getX() - this.position.getX();
        if (distance == 1) {
            return new Cell[0];
        }

        return new Cell[]{board.getCell(to.getX() - 1, to.getY())};
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "position=" + position +
                '}';
    }
}
