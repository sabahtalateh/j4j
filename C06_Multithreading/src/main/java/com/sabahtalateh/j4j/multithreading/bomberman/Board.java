package com.sabahtalateh.j4j.multithreading.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Board.
 */
public class Board {

    private final int size;

    private final ReentrantLock[][] board;

    /**
     * @param size size.
     */
    public Board(int size) {
        this.size = size;
        this.board = new ReentrantLock[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

    /**
     * @return board.
     */
    public ReentrantLock[][] getBoard() {
        return board;
    }

    /**
     * @return size.
     */
    public int getSize() {
        return size;
    }

    /**
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {


//        Board b = new Board(4);

        System.out.println(1);
        Thread.sleep(1000);
        System.out.print("\033[H\033[2J");
        System.out.println(12);
        Thread.sleep(1000);
        System.out.print("\033[H\033[2J");
        System.out.println(123);
        Thread.sleep(1000);
        System.out.print("\033[H\033[2J");
        System.out.flush();


    }
}
