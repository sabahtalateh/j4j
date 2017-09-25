package com.sabahtalateh.j4j.multithreading.bomberman;

import com.sabahtalateh.j4j.multithreading.bomberman.player.Player;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Renderer.
 */
public class Renderer {
    static final String EMPTY_CELL = " . ";
    static final String WALL_CELL = " ▧ ";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * @param board board.
     */
    static void startRendering(Board board) {
        new Thread(new RendererRunnable(board)).start();
    }

    /**
     * RendererRunnable.
     */
    private static class RendererRunnable implements Runnable {
        private final Board board;
        private final List<Player> players;

        /**
         * @param board board.
         */
        private RendererRunnable(Board board) {
            this.board = board;
            this.players = board.getPlayers();
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
                // Clear console.
                System.out.print("\033[H\033[2J");
                for (int x = 0; x < board.getSize(); x++) {
                    for (int y = 0; y < board.getSize(); y++) {
                        int xx = x;
                        int yy = y;

                        List<Player> playersOnCell = players.stream()
                                .filter(p -> p.getCoordinate().getX() == xx && p.getCoordinate().getY() == yy)
                                .collect(Collectors.toList());

                        playersOnCell.sort((p1, p2) -> {
                            if (p1.isEnemy() && !p2.isEnemy()) {
                                return 1;
                            } else {
                                return 0;
                            }
                        });

                        Optional<Player> player = players.stream()
                                .filter(p -> p.getCoordinate().getX() == xx && p.getCoordinate().getY() == yy)
                                .sorted((o1, o2) -> {
                                    if (o1.isEnemy() && !o2.isEnemy()) {
                                        return -1;
                                    } else if (o2.isEnemy() && !o1.isEnemy()) {
                                        return 1;
                                    } else {
                                        return 0;
                                    }
                                })
                                .findFirst();
                        if (player.isPresent()) {
                            if (!player.get().isEnemy()) {
                                if (player.get().isAlive()) {
                                    System.out.printf(" \uD83E\uDD16 ");
                                } else {
                                    System.out.printf(" \uD83D\uDC80 ");
                                }
                            } else {
                                System.out.printf(" \uD83D\uDC7E ");
                            }
                        } else if (board.isWall(x, y)) {
                            System.out.print(ANSI_GREEN);
                            System.out.print(WALL_CELL);
                            System.out.print(ANSI_RESET);
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
