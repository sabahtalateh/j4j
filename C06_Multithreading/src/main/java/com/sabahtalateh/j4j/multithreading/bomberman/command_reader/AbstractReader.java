package com.sabahtalateh.j4j.multithreading.bomberman.command_reader;

import com.sabahtalateh.j4j.multithreading.bomberman.Board;
import com.sabahtalateh.j4j.multithreading.bomberman.player.Direction;
import com.sabahtalateh.j4j.multithreading.bomberman.player.Player;

import java.util.concurrent.BlockingQueue;

/**
 * AbstractReader.
 */
public abstract class AbstractReader implements Runnable {

    private final BlockingQueue<Direction> commandQueue;

    protected final Player player;

    protected final Board board;

    /**
     * @param commandQueue command queue.
     * @param player       player.
     * @param board        board.
     */
    AbstractReader(BlockingQueue<Direction> commandQueue, Player player, Board board) {
        this.commandQueue = commandQueue;
        this.player = player;
        this.board = board;
    }

    /**
     * Run.
     */
    @Override
    public void run() {
        while (true) {
            try {
                commandQueue.put(read());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return direction.
     */
    public abstract Direction read();
}
