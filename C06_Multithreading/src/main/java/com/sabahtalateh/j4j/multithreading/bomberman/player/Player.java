package com.sabahtalateh.j4j.multithreading.bomberman.player;

import com.sabahtalateh.j4j.multithreading.bomberman.Board;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * player.
 */
public class Player implements Runnable {

    private final Board board;

    private Coordinate coordinate;

    private final BlockingQueue<Direction> commandQueue;

    private final Character name;

    private final PlayerType playerType;

    private boolean alive = true;

    /**
     * @param board        board.
     * @param coordinate   coordinate.
     * @param commandQueue command queue.
     * @param name         name.
     * @param playerType   player type.
     */
    public Player(
            Board board,
            Coordinate coordinate,
            BlockingQueue<Direction> commandQueue,
            Character name,
            PlayerType playerType
    ) {
        this.board = board;
        this.coordinate = coordinate;
        this.commandQueue = commandQueue;
        this.name = name;
        this.playerType = playerType;
    }

    /**
     * @throws InterruptedException        exception.
     * @throws CanNotCreatePlayerException exception.
     */
    private void init() throws InterruptedException, CanNotCreatePlayerException {
        ReentrantLock lock = board.getBoard()[coordinate.getX()][coordinate.getY()];
        if (lock == null || !lock.tryLock(500, TimeUnit.MILLISECONDS)) {
            throw new CanNotCreatePlayerException(String.format(
                    "Position [%s, %s] is already taken by wall or another player.",
                    coordinate.getX(),
                    coordinate.getY())
            );
        }
    }

    /**
     * @return name.
     */
    public Character getName() {
        return name;
    }

    /**
     * @return coordinate.
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * @return is enemy.
     */
    public boolean isEnemy() {
        return playerType == PlayerType.ENEMY;
    }

    /**
     * @return is alive.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @param player player.
     */
    public void kill(Player player) {
        player.alive = false;
    }

    /**
     * Run.
     */
    @Override
    public void run() {
        try {
            this.init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (CanNotCreatePlayerException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (this.alive) {
            Coordinate newCoordinate;
            try {
                newCoordinate = this.getNextCoordinate();
                while (!this.canGoTo(newCoordinate)) {
                    newCoordinate = this.getNextCoordinate();
                }
                if (this.playerType == PlayerType.PLAYER && board.hasEnemyOnCoordinate(newCoordinate)) {
                    this.kill(this);
                    continue;
                }
                if (this.playerType == PlayerType.ENEMY) {
                    Player player = board.getPlayerOnCoordinate(newCoordinate);
                    if (player != null) {
                        this.kill(player);
                    }
                }

                if (this.board.tryLockCoordinate(newCoordinate)) {
                    board.unlockCoordinate(this.coordinate);
                    this.coordinate = newCoordinate;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        board.unlockCoordinate(this.coordinate);
    }

    /**
     * @return new coordinate.
     * @throws InterruptedException exception.
     */
    private Coordinate getNextCoordinate() throws InterruptedException {
        Direction direction = this.commandQueue.take();
        Coordinate newCoordinate;
        switch (direction) {
            case UP:
                newCoordinate = new Coordinate(this.getCoordinate().getX() - 1, this.getCoordinate().getY());
                break;
            case DOWN:
                newCoordinate = new Coordinate(this.getCoordinate().getX() + 1, this.getCoordinate().getY());
                break;
            case LEFT:
                newCoordinate = new Coordinate(this.getCoordinate().getX(), this.getCoordinate().getY() - 1);
                break;
            case RIGHT:
                newCoordinate = new Coordinate(this.getCoordinate().getX(), this.getCoordinate().getY() + 1);
                break;
            default:
                newCoordinate = new Coordinate(this.getCoordinate().getX(), this.getCoordinate().getY());
        }
        return newCoordinate;
    }

    /**
     * @param coordinate coordinates.
     * @return can go.
     */
    private boolean canGoTo(Coordinate coordinate) {
        boolean canGoTo = true;
        if (coordinate.getX() < 0
                || coordinate.getY() < 0
                || coordinate.getX() >= board.getSize()
                || coordinate.getY() >= board.getSize()
                || this.board.isWall(coordinate.getX(), coordinate.getY())
                ) {
            canGoTo = false;
        }
        return canGoTo;
    }

}
