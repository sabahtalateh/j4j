package com.sabahtalateh.jenkov_tutorials.io.input_stream_reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
