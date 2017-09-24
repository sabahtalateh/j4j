package com.sabahtalateh.j4j.multithreading.bomberman;

import com.sabahtalateh.j4j.multithreading.bomberman.Player.Player;

import java.util.List;

/**
 * Renderer.
 */
public class Renderer {
    static final String EMPTY_CELL = " . ";

    static final String PLAYER = " P ";

    /**
     * @param board   board.
     * @param players players.
     */
    static void startRendering(Board board, List<Player> players) {
        new Thread(new RendererRunnable(board, players)).start();
    }

    /**
     * RendererRunnable.
     */
    private static class RendererRunnable implements Runnable {
        private final Board board;
        private final List<Player> players;

        /**
         * @param board   board.
         * @param players players.
         */
        private RendererRunnable(Board board, List<Player> players) {
            this.board = board;
            this.players = players;
        }


        /**
         * Run.
         */
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("\033[H\033[2J");
                for (int x = 0; x < board.getSize(); x++) {
                    for (int y = 0; y < board.getSize(); y++) {
                        int xx = x;
                        int yy = y;
                        if (players.stream().anyMatch(p -> p.getCoordinate().getX() == xx && p.getCoordinate().getY() == yy)) {
                            System.out.print(PLAYER);
                        } else {
                            System.out.print(EMPTY_CELL);
                        }
                    }
                    System.out.println();
                }
            }
        }
    }
}
