package com.sabahtalateh.j4j.oop.chess.board;

import com.sabahtalateh.j4j.oop.chess.board.figure.CanNotGoException;
import com.sabahtalateh.j4j.oop.chess.board.figure.Figure;
import com.sabahtalateh.j4j.oop.chess.board.figure.Pawn;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * BoardTest.
 */
public class BoardTest {
    @Test
    public void testEmptyBoardCreation() throws Exception {
        Board board = new Board(8);

        assertThat(board.getCell(0, 0), instanceOf(Cell.class));
        assertThat(board.getCell(7, 7), instanceOf(Cell.class));
    }

    @Test(expected = CellIsOccupiedException.class)
    public void exceptionThrowingIfFigureIsNotMoved() throws Exception {
        Board board = new Board(8);
        board.addFigure(new Figure(board.getCell(0, 0)) {
            @Override
            public boolean canGoTo(Cell position) throws CanNotGoException {
                return true;
            }

            @Override
            public void afterGoRoutines() {

            }

            @Override
            public Cell[] getPathCells(Cell to, Board board) throws NoSuchCellException {
                return new Cell[0];
            }
        });
        board.move(0, 0, 0, 0);
    }

    @Test(expected = CanNotGoException.class)
    public void pawnCanOnlyGoVertically() throws Exception {
        Board board = new Board(8);
        board.addFigure(new Pawn(4, 4));
        board.move(4, 4, 4, 5);
    }

    @Test(expected = CanNotGoException.class)
    public void pawnCanOnlyGoForward() throws Exception {
        Board board = new Board(8);
        board.addFigure(new Pawn(4, 4));
        board.move(4, 4, 3, 4);
    }

    @Test(expected = CanNotGoException.class)
    public void pawnCanNotGoTwoCellsAfterFirstMove() throws Exception {
        Board board = new Board(8);
        board.addFigure(new Pawn(0, 0));
        board.move(0, 0, 2, 0);
        board.move(2, 0, 4, 0);
    }

    @Test(expected = CanNotGoException.class)
    public void pawnCanNotGoMoreThanTwoCells() throws Exception {
        Board board = new Board(8);
        board.addFigure(new Pawn(0, 0));
        board.move(0, 0, 3, 0);
    }

    @Test(expected = WayIsOccupiedException.class)
    public void canNotGoThroughOtherFigures() throws Exception {
        Board board = new Board(8);
        board.addFigure(new Pawn(1, 0));
        board.addFigure(new Pawn(1, 1));
        board.addFigure(new Pawn(1, 2));
        board.addFigure(new Pawn(1, 3));
        board.addFigure(new Pawn(1, 4));
        board.addFigure(new Pawn(1, 5));
        board.addFigure(new Pawn(1, 6));
        board.addFigure(new Pawn(1, 7));

        board.addFigure(new Pawn(0, 3));
        board.move(0, 3, 2, 3);
    }
}
