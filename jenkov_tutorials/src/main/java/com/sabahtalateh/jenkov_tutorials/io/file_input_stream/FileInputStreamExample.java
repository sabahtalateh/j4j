package com.sabahtalateh.jenkov_tutorials.io.file_input_stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * FileInputStreamExample.
 */
public class FileInputStreamExample {
    /**
     * @param args args.
     * @throws IOException exception.
     */
    public static void main(String[] args) throws IOException {

        try (InputStream input = new FileInputStream(new File("jenkov_tutorials/in/example.in"))) {
            int byteRead = input.read();

            while (byteRead != -1) {
                System.out.print((char) byteRead);
                byteRead = input.read();
            }
        }
    }
}
