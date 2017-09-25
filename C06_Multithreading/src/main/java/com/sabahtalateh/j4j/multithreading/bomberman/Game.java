package com.sabahtalateh.j4j.multithreading.bomberman;

//import com.sabahtalateh.j4j.multithreading.bomberman.command_reader.EmptyReader;
//import com.sabahtalateh.j4j.multithreading.bomberman.command_reader.KeyboardReader;

//import com.sabahtalateh.j4j.multithreading.bomberman.command_reader.RandomReader;
import com.sabahtalateh.j4j.multithreading.bomberman.player.*;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.BlockingQueue;

/**
 * Game.
 */
public class Game {
    /**
     * @param args args.
     * @throws CanNotCreatePlayerException exception.
     * @throws InterruptedException        exception.
     */
    public static void main(String[] args) throws CanNotCreatePlayerException, InterruptedException {

        EnvironmentCreationHelper.createEnvironment(
                10,
                10,
                10,
                new boolean[10][10],
                false
        );

//        int size = 7;
//        boolean[][] walls = new boolean[size][size];
//        walls[2][2] = true;
//        walls[2][3] = true;
//        walls[2][4] = true;
//        walls[3][2] = true;
//        walls[4][2] = true;
//        walls[5][2] = true;
//        walls[5][2] = true;
//        walls[5][3] = true;
//        walls[5][4] = true;
//        walls[5][5] = true;
//
//        List<Player> players = new ArrayList<>();
//
//        Board board = new Board(size, walls, players);
//
//        BlockingQueue<Direction> p1CommandQueue = new ArrayBlockingQueue<>(16);
//        Player p1 = new Player(board, new Coordinate(0, 0), p1CommandQueue, '1', PlayerType.PLAYER);
////        KeyboardReader p1CommandReader = new KeyboardReader(p1CommandQueue, p1, board);
////        EmptyReader p1CommandReader = new EmptyReader(p1CommandQueue, p1, board);
//        RandomReader p1CommandReader = new RandomReader(p1CommandQueue, p1, board);
//
//        BlockingQueue<Direction> p2CommandQueue = new ArrayBlockingQueue<>(16);
//        Player p2 = new Player(board, new Coordinate(1, 1), p2CommandQueue, '2', PlayerType.ENEMY);
//        RandomReader p2CommandReader = new RandomReader(p2CommandQueue, p2, board);
////        RandomReader p2CommandReader = new RandomReader(p2CommandQueue, p2, board);
//
//        BlockingQueue<Direction> p3CommandQueue = new ArrayBlockingQueue<>(16);
//        Player p3 = new Player(board, new Coordinate(6, 6), p3CommandQueue, 'E', PlayerType.ENEMY);
//        RandomReader p3CommandReader = new RandomReader(p3CommandQueue, p3, board);
//
//        players.add(p1);
//        players.add(p2);
//        players.add(p3);
//
//        Thread p1t = new Thread(p1);
//        Thread p2t = new Thread(p2);
//        Thread p3t = new Thread(p3);
//
//        p1t.start();
//        p2t.start();
//        p3t.start();
//
//        new Thread(p1CommandReader).start();
//        new Thread(p2CommandReader).start();
//        new Thread(p3CommandReader).start();
//
//        Renderer.startRendering(board);
    }
}
