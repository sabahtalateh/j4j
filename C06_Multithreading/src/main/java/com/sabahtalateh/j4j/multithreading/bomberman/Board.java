package com.sabahtalateh.j4j.multithreading.bomberman;

import com.sabahtalateh.j4j.multithreading.bomberman.player.Coordinate;
import com.sabahtalateh.j4j.multithreading.bomberman.player.Player;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Board.
 */
public class Board {

    private final int size;

    private final ReentrantLock[][] board;

    private final boolean[][] walls;

    private final List<Player> players;

    /**
     * @param size    size.
     * @param walls   walls coordinates.
     * @param players players.
     */
    public Board(int size, boolean[][] walls, List<Player> players) {
        this.size = size;
        this.board = new ReentrantLock[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (!walls[x][y]) {
                    board[x][y] = new ReentrantLock();
                }
            }
        }
        this.walls = walls;
        this.players = players;
    }

    /**
     * @return board.
     */
    public ReentrantLock[][] getBoard() {
        return board;
    }

    /**
     * @return players.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * @return size.
     */
    public int getSize() {
        return size;
    }

    /**
     * @param x x.
     * @param y y.
     * @return is wall.
     */
    public boolean isWall(int x, int y) {
        return walls[x][y];
    }

    /**
     * @param coordinate coordinate.
     * @return try lock result.
     * @throws InterruptedException exception.
     */
    public boolean tryLockCoordinate(Coordinate coordinate) throws InterruptedException {
        return board[coordinate.getX()][coordinate.getY()].tryLock(500, TimeUnit.MILLISECONDS);
    }

    /**
     * @param coordinate coordinate.
     */
    public void unlockCoordinate(Coordinate coordinate) {
        board[coordinate.getX()][coordinate.getY()].unlock();
    }

    /**
     * @param coordinate coordinate.
     * @return is enemy on coordinate.
     */
    public boolean hasEnemyOnCoordinate(Coordinate coordinate) {
        return players.stream()
                .anyMatch(p -> p.isEnemy()
                        && p.getCoordinate().getX() == coordinate.getX()
                        && p.getCoordinate().getY() == coordinate.getY()
                );

    }

    /**
     * @param coordinate coordinate.
     * @return player.
     */
    public Player getPlayerOnCoordinate(Coordinate coordinate) {
        return players.stream().filter(p -> !p.isEnemy()
                && p.getCoordinate().getX() == coordinate.getX()
                && p.getCoordinate().getY() == coordinate.getY()
        ).findFirst().orElse(null);
    }
}
