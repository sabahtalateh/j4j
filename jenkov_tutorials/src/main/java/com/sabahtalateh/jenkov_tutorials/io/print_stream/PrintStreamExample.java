package com.sabahtalateh.jenkov_tutorials.io.print_stream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * PrintStreamExample.
 */
public class PrintStreamExample {
    /**
     * @param args args.
     * @throws FileNotFoundException exception.
     */
    public static void main(String[] args) throws FileNotFoundException {
        try (PrintStream out = new PrintStream(new FileOutputStream("jenkov_tutorials/out/example_print_stream.out"))) {
            out.println(14);
            out.println("Hello!");
        }
    }
}
