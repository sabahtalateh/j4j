package com.sabahtalateh.j4j.oop.chess.board;

import com.sabahtalateh.j4j.oop.chess.board.figure.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.is;
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
            public Cell[] getPathThroughCells(Cell to, Board board) throws NoSuchCellException {
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

    @Test(expected = CanNotGoException.class)
    public void exceptionThrowingWhenKnightGoNotL() throws Exception {
        Board board = new Board(8);
        board.addFigure(new Knight(4, 4));
        board.move(4, 4, 6, 6);
    }

    @Test
    public void knightCanGoL() throws Exception {
        Board board = new Board(8);
        Figure knight = new Knight(4, 4);
        board.addFigure(knight);
        board.move(4, 4, 6, 5);
        assertThat(knight.getPosition(), is(board.getCell(6, 5)));
    }

    @Test
    public void knightCanJumpOverOtherFigures() throws Exception {
        Board board = new Board(8);
        board.addFigure(new Pawn(1, 0));
        board.addFigure(new Pawn(1, 1));
        board.addFigure(new Pawn(1, 2));
        board.addFigure(new Pawn(1, 3));
        board.addFigure(new Pawn(1, 4));
        board.addFigure(new Pawn(1, 5));
        board.addFigure(new Pawn(1, 6));
        board.addFigure(new Pawn(1, 7));

        Figure knight = new Knight(0, 0);
        board.addFigure(knight);
        board.move(0, 0, 2, 1);
        assertThat(knight.getPosition(), is(board.getCell(2, 1)));
    }

    @Test(expected = CanNotGoException.class)
    public void exceptionThrowingWhenCastleGoesNonVerticallyOrHorizontally() throws Exception {
        Board board = new Board(8);
        board.addFigure(new Castle(0, 0));
        board.move(0, 0, 4, 2);
    }

    @Test
    public void castleCanGoVerticallyOrHorizontally() throws Exception {
        Board board = new Board(8);
        Figure castle = new Castle(0, 0);
        board.addFigure(castle);
        board.move(0, 0, 5, 0);
        assertThat(castle.getPosition(), is(board.getCell(5, 0)));
    }

    @Test(expected = WayIsOccupiedException.class)
    public void castleCanNotGoThroughOtherFigures() throws Exception {
        Board board = new Board(8);
        board.addFigure(new Castle(0, 0));

        board.addFigure(new Pawn(1, 0));
        board.addFigure(new Pawn(1, 1));
        board.addFigure(new Pawn(1, 2));
        board.addFigure(new Pawn(1, 3));
        board.addFigure(new Pawn(1, 4));
        board.addFigure(new Pawn(1, 5));
        board.addFigure(new Pawn(1, 6));
        board.addFigure(new Pawn(1, 7));

        board.move(0, 0, 5, 0);
    }

    @Test(expected = CanNotGoException.class)
    public void exceptionThrowingIfBishopGoesNotDiagonally() throws Exception {
        Board board = new Board(8);
        board.addFigure(new Bishop(0, 0));
        board.move(0, 0, 2, 5);
    }

    @Test
    public void bishopCanGoDiagonally() throws Exception {
        Board board = new Board(8);
        Figure bishop = new Bishop(0, 0);
        board.addFigure(bishop);
        board.move(0, 0, 4, 4);
        assertThat(bishop.getPosition(), is(board.getCell(4, 4)));
    }

    @Test(expected = WayIsOccupiedException.class)
    public void bishopCanNotGoThroughOtherFigures() throws Exception {
        Board board = new Board(8);
        board.addFigure(new Bishop(0, 0));

        board.addFigure(new Pawn(1, 0));
        board.addFigure(new Pawn(1, 1));
        board.addFigure(new Pawn(1, 2));
        board.addFigure(new Pawn(1, 3));
        board.addFigure(new Pawn(1, 4));
        board.addFigure(new Pawn(1, 5));
        board.addFigure(new Pawn(1, 6));
        board.addFigure(new Pawn(1, 7));

        board.move(0, 0, 7, 7);
    }
}
