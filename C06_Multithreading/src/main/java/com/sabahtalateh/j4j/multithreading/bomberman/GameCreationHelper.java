package com.sabahtalateh.j4j.multithreading.bomberman;

import com.sabahtalateh.j4j.multithreading.bomberman.command_reader.AbstractReader;
import com.sabahtalateh.j4j.multithreading.bomberman.command_reader.KeyboardReader;
import com.sabahtalateh.j4j.multithreading.bomberman.command_reader.RandomReader;
import com.sabahtalateh.j4j.multithreading.bomberman.player.Coordinate;
import com.sabahtalateh.j4j.multithreading.bomberman.player.Direction;
import com.sabahtalateh.j4j.multithreading.bomberman.player.Player;
import com.sabahtalateh.j4j.multithreading.bomberman.player.PlayerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * GameCreationHelper.
 */
public class GameCreationHelper {
    /**
     * @param playersCount       players count.
     * @param enemiesCount       enemies count.
     * @param boardSize          board size.
     * @param walls              walls map.
     * @param withKeyboardPlayer will be the keyboard player.
     */
    public static void createAndStart(
            int boardSize,
            int playersCount,
            int enemiesCount,
            boolean[][] walls,
            boolean withKeyboardPlayer
    ) {
        List<Player> players = new ArrayList<>();
        Board board = new Board(boardSize, walls, players);

        List<Coordinate> freeCells = new ArrayList<>();
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                if (!board.isWall(x, y)) {
                    freeCells.add(new Coordinate(x, y));
                }
            }
        }

        if (freeCells.size() < (playersCount + enemiesCount + (withKeyboardPlayer ? 1 : 0))) {
            throw new IllegalArgumentException("Not enough cells to create such amount of players.");
        }

        Random random = new Random();
        List<AbstractReader> commandReaders = new ArrayList<>();
        for (int i = 0; i < playersCount; i++) {
            BlockingQueue<Direction> commandQueue = new ArrayBlockingQueue<>(16);
            Player player = new Player(
                    board,
                    freeCells.remove(random.nextInt(freeCells.size())),
                    commandQueue,
                    Character.forDigit(i, 10),
                    PlayerType.PLAYER,
                    false
            );
            RandomReader commandReader = new RandomReader(commandQueue, player, board);
            commandReaders.add(commandReader);
            players.add(player);
        }

        for (int i = 0; i < enemiesCount; i++) {
            BlockingQueue<Direction> commandQueue = new ArrayBlockingQueue<>(16);
            Player player = new Player(
                    board,
                    freeCells.remove(random.nextInt(freeCells.size())),
                    commandQueue,
                    Character.forDigit(i, 10),
                    PlayerType.ENEMY,
                    false
            );
            RandomReader commandReader = new RandomReader(commandQueue, player, board);
            commandReaders.add(commandReader);
            players.add(player);
        }

        if (withKeyboardPlayer) {
            BlockingQueue<Direction> commandQueue = new ArrayBlockingQueue<>(16);
            Player player = new Player(
                    board,
                    freeCells.remove(random.nextInt(freeCells.size())),
                    commandQueue,
                    'P',
                    PlayerType.PLAYER,
                    true
            );
            KeyboardReader commandReader = new KeyboardReader(commandQueue, player, board);
            commandReaders.add(commandReader);
            players.add(player);
        }

        players.forEach(p -> new Thread(p).start());
        commandReaders.forEach(c -> new Thread(c).start());
        Renderer.startRendering(board);
    }
}
