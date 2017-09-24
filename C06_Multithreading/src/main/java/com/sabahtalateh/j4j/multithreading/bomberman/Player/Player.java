package com.sabahtalateh.j4j.multithreading.bomberman.Player;

import com.sabahtalateh.j4j.multithreading.bomberman.Board;
import com.sabahtalateh.j4j.multithreading.bomberman.CanNotCreatePlayerException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Player.
 */
public class Player implements Runnable {

    private final Board board;

    private Coordinate coordinate;

    private Random random = new Random();

    private static Direction[] directions = Direction.values();

    /**
     * @param board      board.
     * @param coordinate coordinate.
     */
    public Player(Board board, Coordinate coordinate) {
        this.board = board;
        this.coordinate = coordinate;
    }

    /**
     * @throws InterruptedException        exception.
     * @throws CanNotCreatePlayerException exception.
     */
    private void init() throws InterruptedException, CanNotCreatePlayerException {
        ReentrantLock lock = board.getBoard()[coordinate.getX()][coordinate.getY()];
        if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) {
            throw new CanNotCreatePlayerException(String.format(
                    "Position [%s, %s] is already taken.",
                    coordinate.getX(),
                    coordinate.getY())
            );
        }
    }

    /**
     * @return coordinate.
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Run.
     */
    @Override
    public void run() {
        try {
            this.init();
        } catch (InterruptedException | CanNotCreatePlayerException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.coordinate = this.getNextCoordinate();
        }
    }


    /**
     * @return new coordinate.
     */
    private Coordinate getNextCoordinate() {
        Direction direction = this.decideWhereToGo();
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
            default: // case RIGHT:
                newCoordinate = new Coordinate(this.getCoordinate().getX(), this.getCoordinate().getY() + 1);
                break;
        }
        return newCoordinate;
    }

    /**
     * @return direction.
     */
    private Direction decideWhereToGo() {
        List<Direction> directions = new ArrayList<>(Arrays.asList(Player.directions));
        if (this.getCoordinate().getX() == 0) {
            directions.remove(Direction.UP);
        }
        if (this.getCoordinate().getX() == board.getSize() - 1) {
            directions.remove(Direction.DOWN);
        }
        if (this.getCoordinate().getY() == 0) {
            directions.remove(Direction.LEFT);
        }
        if (this.getCoordinate().getY() == board.getSize() - 1) {
            directions.remove(Direction.RIGHT);
        }
        return directions.get(random.nextInt(directions.size()));
    }

}
