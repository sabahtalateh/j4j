package com.sabahtalateh.j4j.oop.chess.board.figure;

import com.sabahtalateh.j4j.oop.chess.board.Board;
import com.sabahtalateh.j4j.oop.chess.board.Cell;
import com.sabahtalateh.j4j.oop.chess.board.NoSuchCellException;

/**
 * Figure.
 */
public abstract class Figure {
    Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    public void go(Cell position) throws CanNotGoException {
        if (this.canGoTo(position)) {
            this.position = position;
            this.afterGoRoutines();
        }
    }

    public abstract boolean canGoTo(Cell position) throws CanNotGoException;

    public abstract void afterGoRoutines();

    public Cell getPosition() {
        return this.position;
    }

    public abstract Cell[] getPathCells(Cell to, Board board) throws NoSuchCellException;
}
