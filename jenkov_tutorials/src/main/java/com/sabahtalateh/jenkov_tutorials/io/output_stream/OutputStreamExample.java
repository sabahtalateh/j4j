package com.sabahtalateh.jenkov_tutorials.io.output_stream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
