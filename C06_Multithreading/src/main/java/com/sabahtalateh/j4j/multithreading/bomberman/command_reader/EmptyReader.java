package com.sabahtalateh.j4j.multithreading.bomberman.command_reader;

import com.sabahtalateh.j4j.multithreading.bomberman.Board;
import com.sabahtalateh.j4j.multithreading.bomberman.player.Direction;
import com.sabahtalateh.j4j.multithreading.bomberman.player.Player;

import java.util.concurrent.BlockingQueue;

/**
 * RandomReader.
 */
public class EmptyReader extends AbstractReader {

    /**
     * @param commandQueue command queue.
     * @param player       player.
     * @param board        board.
     */
    public EmptyReader(BlockingQueue<Direction> commandQueue, Player player, Board board) {
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
        return Direction.STAY;
    }
}
