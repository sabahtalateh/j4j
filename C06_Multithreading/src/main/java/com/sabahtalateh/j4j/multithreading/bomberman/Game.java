package com.sabahtalateh.j4j.multithreading.bomberman;

import com.sabahtalateh.j4j.multithreading.bomberman.Player.Coordinate;
import com.sabahtalateh.j4j.multithreading.bomberman.Player.Player;

import java.util.ArrayList;
import java.util.List;

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
        Board board = new Board(10);
        List<Player> players = new ArrayList<Player>() {{
            add(new Player(board, new Coordinate(0, 0)));
//            add(new Player(board, new Coordinate(3, 2)));
        }};
        players.forEach(p -> new Thread(p).start());
        Renderer.startRendering(board, players);

//        Player p1 = new Player(board, 0, 0);
//        Thread p1 = new Thread();
//        p1.start();
    }
}
