package com.sabahtalateh.j4j.multithreading.bomberman.command_reader;

import com.sabahtalateh.j4j.multithreading.bomberman.Board;
import com.sabahtalateh.j4j.multithreading.bomberman.player.Direction;
import com.sabahtalateh.j4j.multithreading.bomberman.player.Player;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;

/**
 * RandomReader.
 */
public class KeyboardReader extends AbstractReader {

    private static String ttyConfig;

    /**
     * @param commandQueue command queue.
     * @param player       player.
     * @param board        board.
     */
    public KeyboardReader(BlockingQueue<Direction> commandQueue, Player player, Board board) {
        super(commandQueue, player, board);
    }

    /**
     * @return direction.
     */
    @Override
    public Direction read() {
        Direction direction = null;
        while (direction == null) {
            try {
                setTerminalToCBreak();
                if (System.in.available() != 0) {
                    int c0 = System.in.read();
                    int c1 = System.in.read();
                    int c2 = System.in.read();

                    if (c0 == 27 && c1 == 91 && c2 == 68) {
                        direction = Direction.LEFT;
                    }
                    if (c0 == 27 && c1 == 91 && c2 == 66) {
                        direction = Direction.DOWN;
                    }
                    if (c0 == 27 && c1 == 91 && c2 == 67) {
                        direction = Direction.RIGHT;
                    }
                    if (c0 == 27 && c1 == 91 && c2 == 65) {
                        direction = Direction.UP;
                    }
                }
            } catch (IOException e) {
                System.err.println("IOException");
            } catch (InterruptedException e) {
                System.err.println("InterruptedException");
            } finally {
                try {
                    stty(ttyConfig.trim());
                } catch (Exception e) {
                    System.err.println("Exception restoring tty configuration");
                }
            }
        }
        return direction;
    }


    /**
     * @throws IOException          exception
     * @throws InterruptedException exception.
     */
    private static void setTerminalToCBreak() throws IOException, InterruptedException {

        ttyConfig = stty("-g");

        // set the console to be character-buffered instead of line-buffered
        stty("-icanon min 1");

        // disable character echoing
        stty("-echo");
    }

    /**
     * @param args args.
     * @return string.
     * @throws IOException          exception.
     * @throws InterruptedException exception.
     */
    private static String stty(final String args)
            throws IOException, InterruptedException {
        String cmd = "stty " + args + " < /dev/tty";

        return exec(new String[]{"sh", "-c", cmd});
    }

    /**
     * @param cmd command.
     * @return string.
     * @throws IOException          exception.
     * @throws InterruptedException exception.
     */
    private static String exec(final String[] cmd)
            throws IOException, InterruptedException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        Process p = Runtime.getRuntime().exec(cmd);
        int c;
        InputStream in = p.getInputStream();

        while ((c = in.read()) != -1) {
            bout.write(c);
        }

        in = p.getErrorStream();

        while ((c = in.read()) != -1) {
            bout.write(c);
        }

        p.waitFor();

        String result = new String(bout.toByteArray());
        return result;
    }
}
