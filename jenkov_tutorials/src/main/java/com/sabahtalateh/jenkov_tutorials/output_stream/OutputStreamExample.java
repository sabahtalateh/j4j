package com.sabahtalateh.jenkov_tutorials.output_stream;

import java.io.*;

/**
 * OutputStreamExample.
 */
public class OutputStreamExample {
    /**
     * @param args args.
     * @throws IOException exception.
     */
    public static void main(String[] args) throws IOException {

        try (OutputStream out = new FileOutputStream("jenkov_tutorials/out/example.out", true);) {
            out.write("Hello World".getBytes());
            out.write("\n".getBytes());
            out.flush();
        }
    }
}
