package com.sabahtalateh.jenkov_tutorials.io.pushback_input_stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * PushbackInputStreamExample.
 */
public class PushbackInputStreamExample {
    /**
     * @param args args.
     * @throws IOException exception.
     */
    public static void main(String[] args) throws IOException {
        PushbackInputStream pbis = new PushbackInputStream(new FileInputStream(new File("jenkov_tutorials/in/example.in")), 12);
        byte[] bytes = new byte[4];


        pbis.read(bytes);
        for (byte b : bytes) {
            System.out.print((char) b);
        }

        pbis.read(bytes);
        for (byte b : bytes) {
            System.out.print((char) b);
        }

        pbis.unread(bytes);

        pbis.read(bytes);
        for (byte b : bytes) {
            System.out.print((char) b);
        }
    }
}
