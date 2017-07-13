package com.sabahtalateh.j4j.oop.chess.board;

import com.sabahtalateh.j4j.oop.chess.board.figure.CanNotGoException;
import com.sabahtalateh.j4j.oop.chess.board.figure.Figure;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Board.
 */
public class Board {
    private Cell[][] cells;

    private List<Figure> figures = new LinkedList<>();

    /**
     * @param size of the board.
     */
    public Board(int size) {
        cells = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * @param figure to add.
     * @throws ImpossibleToAddFigureException if cell is taken.
     */
    public void addFigure(Figure figure) throws ImpossibleToAddFigureException {
        if (this.figures.stream().anyMatch((f -> f.getPosition().equals(figure.getPosition())))) {
            throw new ImpossibleToAddFigureException("Cell of figure you trying to add is already taken.");
        }

        figures.add(figure);
    }

    /**
     * @param from where.
     * @param to   where.
     * @throws FigureNotFoundException if there is no figure.
     * @throws CanNotGoException       if figure can not go.
     * @throws CellIsOccupiedException if `cell to` is occupied.
     * @throws NoSuchCellException     if no such cell on the board.
     * @throws WayIsOccupiedException  if way is occupied.
     */
    public void move(Cell from, Cell to) throws FigureNotFoundException, CanNotGoException, CellIsOccupiedException, NoSuchCellException, WayIsOccupiedException {
        Optional<Figure> figureFrom = this.figures.stream().filter(figure -> from.equals(figure.getPosition())).findFirst();
        if (!figureFrom.isPresent()) {
            throw new FigureNotFoundException("There is no figure in `from` cell");
        }

        if (this.figures.stream().anyMatch(figure -> to.equals(figure.getPosition()))) {
            throw new CellIsOccupiedException("Cannot mode figure to occupied cell");
        }

        Cell[] pathCells = figureFrom.get().getPathThroughCells(to, this);
        for (Cell c : pathCells) {
            if (this.figures.stream().anyMatch(figure -> figure.getPosition().equals(c))) {
                throw new WayIsOccupiedException("Cannot go over other figures");
            }
        }

        figureFrom.get().go(to);
    }

    /**
     * @param x1 coordinate.
     * @param y1 coordinate.
     * @param x2 coordinate.
     * @param y2 coordinate.
     * @throws FigureNotFoundException if there is no figure.
     * @throws CanNotGoException       if figure can not go.
     * @throws CellIsOccupiedException if `cell to` is occupied.
     * @throws NoSuchCellException     if no such cell on the board.
     * @throws WayIsOccupiedException  if way is occupied.
     */
    public void move(int x1, int y1, int x2, int y2) throws NoSuchCellException, FigureNotFoundException, CanNotGoException, CellIsOccupiedException, WayIsOccupiedException {
        this.move(this.getCell(x1, y1), this.getCell(x2, y2));
    }

    /**
     * @param x coordinate.
     * @param y coordinate.
     * @return cell.
     * @throws NoSuchCellException if no such cell.
     */
    public Cell getCell(int x, int y) throws NoSuchCellException {
        if (!this.cellBelongsToBoard(new Cell(x, y))) {
            throw new NoSuchCellException(String.format("No cell {%s, %s}", x, y));
        }

        return this.cells[x][y];
    }

    /**
     * @param cell to check.
     * @return result.
     */
    private boolean cellBelongsToBoard(Cell cell) {
        return !(cell.getX() >= this.cells.length || cell.getY() >= this.cells[0].length || cell.getX() < 0 || cell.getY() < 0);
    }
}
