package com.sabahtalateh.jenkov_tutorials.input_stream_reader;

import java.io.*;

/**
 * InputStreamReaderExample.
 */
public class InputStreamReaderExample {
    /**
     * @param args args.
     * @throws IOException exception.
     */
    public static void main(String[] args) throws IOException {
        try (InputStreamReader isr
                     = new InputStreamReader(new FileInputStream(new File("jenkov_tutorials/in/file.txt")))) {
            System.out.println((char) isr.read());
            System.out.println((char) isr.read());
            System.out.println((char) isr.read());
            System.out.println((char) isr.read());
            System.out.println((char) isr.read());
            System.out.println((char) isr.read());
            System.out.println((char) isr.read());
            System.out.println((char) isr.read());
            System.out.println((char) isr.read());
            System.out.println((char) isr.read());
            System.out.println((char) isr.read());
            System.out.println((char) isr.read());
        }

    }
}
