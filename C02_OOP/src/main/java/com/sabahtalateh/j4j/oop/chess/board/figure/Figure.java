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

    /**
     * @param position of figure.
     */
    public Figure(Cell position) {
        this.position = position;
    }

    /**
     * @param x coordinate.
     * @param y coordinate.
     */
    public Figure(int x, int y) {
        this(new Cell(x, y));
    }

    /**
     * @param position to go.
     * @throws CanNotGoException when can not go.
     */
    public void go(Cell position) throws CanNotGoException {
        if (!this.canGoTo(position)) {
            throw new CanNotGoException(this.getCanNotGoReason());
        }
        this.position = position;
        this.afterGoRoutines();
    }

    /**
     * @param position check if can go.
     * @return result.
     * @throws CanNotGoException if can not go.
     */
    public abstract boolean canGoTo(Cell position) throws CanNotGoException;

    /**
     * Actions after movement.
     */
    public abstract void afterGoRoutines();

    /**
     * @return figure's position.
     */
    public Cell getPosition() {
        return this.position;
    }

    /**
     * @param to    go.
     * @param board board.
     * @return cells.
     * @throws NoSuchCellException if no cells.
     */
    public abstract Cell[] getPathThroughCells(Cell to, Board board) throws NoSuchCellException;

    /**
     * @param canNotGoReason reason.
     */
    void setCanNotGoReason(String canNotGoReason) {
        this.canNotGoReason = canNotGoReason;
    }

    /**
     * @return reason why figure can not go.
     */
    private String getCanNotGoReason() {
        return this.canNotGoReason;
    }
}
