package com.sabahtalateh.j4j.oop.chess.board.figure;

import com.sabahtalateh.j4j.oop.chess.board.Board;
import com.sabahtalateh.j4j.oop.chess.board.Cell;
import com.sabahtalateh.j4j.oop.chess.board.NoSuchCellException;

/**
 * Figure.
 */
public abstract class Figure {

    private String canNotGoReason = "";

    Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    public Figure(int x, int y) {
        this(new Cell(x, y));
    }

    public void go(Cell position) throws CanNotGoException {
        if (!this.canGoTo(position)) {
            throw new CanNotGoException(this.getCanNotGoReason());
        }
        this.position = position;
        this.afterGoRoutines();
    }

    public abstract boolean canGoTo(Cell position) throws CanNotGoException;

    public abstract void afterGoRoutines();

    public Cell getPosition() {
        return this.position;
    }

    public abstract Cell[] getPathThroughCells(Cell to, Board board) throws NoSuchCellException;

    public void setCanNotGoReason(String canNotGoReason) {
        this.canNotGoReason = canNotGoReason;
    }

    protected String getCanNotGoReason() {
        return this.canNotGoReason;
    };
}
