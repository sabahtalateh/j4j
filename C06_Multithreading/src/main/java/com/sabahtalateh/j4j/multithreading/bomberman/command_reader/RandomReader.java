package com.sabahtalateh.j4j.multithreading.bomberman.command_reader;

import com.sabahtalateh.j4j.multithreading.bomberman.Board;
import com.sabahtalateh.j4j.multithreading.bomberman.player.Direction;
import com.sabahtalateh.j4j.multithreading.bomberman.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * RandomReader.
 */
public class RandomReader extends AbstractReader {

    private Random random = new Random();

    /**
     * @param commandQueue command queue.
     * @param player       player.
     * @param board        board.
     */
    public RandomReader(BlockingQueue<Direction> commandQueue, Player player, Board board) {
        super(commandQueue, player, board);
    }

    /**
     * @return direction.
     */
    @Override
    public Direction read() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));
        if (player.getCoordinate().getX() == 0) {
            directions.remove(Direction.UP);
        }
        if (player.getCoordinate().getX() == board.getSize() - 1) {
            directions.remove(Direction.DOWN);
        }
        if (player.getCoordinate().getY() == 0) {
            directions.remove(Direction.LEFT);
        }
        if (player.getCoordinate().getY() == board.getSize() - 1) {
            directions.remove(Direction.RIGHT);
        }
        return directions.get(random.nextInt(directions.size()));
    }
}
