package com.sabahtalateh.jenkov_tutorials.pipes;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * PipesExample.
 */
public class PipesExample {
    /**
     * @param args args.
     * @throws IOException exception.
     */
    public static void main(String[] args) throws IOException {
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = new PipedInputStream();
        pipedOutputStream.connect(pipedInputStream);

        new Thread(() -> {
            try {
                pipedOutputStream.write("Hello world!".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                int d = pipedInputStream.read();
                while (d != -1) {
                    System.out.printf("%s", (char) d);
                    d = pipedInputStream.read();
                }
            } catch (IOException e) {
            }
        }).start();
    }
}
