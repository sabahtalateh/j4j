package com.sabahtalateh.j4j.multithreading.bomberman;

import com.sabahtalateh.j4j.multithreading.bomberman.player.CanNotCreatePlayerException;

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

        int size = Integer.valueOf(args[0]);

        boolean[][] walls = new boolean[size][size];
        walls[2][2] = true;
        walls[2][3] = true;
        walls[2][4] = true;
        walls[3][2] = true;
        walls[4][2] = true;
        walls[5][2] = true;
        walls[5][2] = true;
        walls[5][3] = true;
        walls[5][4] = true;
        walls[5][5] = true;
        walls[5][6] = true;
        walls[6][6] = true;
        walls[7][6] = true;
        walls[7][5] = true;
        walls[1][8] = true;
        walls[2][8] = true;
        walls[3][8] = true;
        walls[5][8] = true;
        walls[6][8] = true;
        walls[7][8] = true;

        GameCreationHelper.createAndStart(
                size,
                Integer.valueOf(args[1]),
                Integer.valueOf(args[2]),
                walls,
                args[3].equals("true")
        );
    }
}
