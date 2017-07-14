package com.sabahtalateh.j4j.collections_lite.list_performance_comparison;

import java.io.*;
import java.util.UUID;

/**
 * TestFileGenerator.
 */
public class TestFileGenerator {
    /**
     * Generate big file.
     *
     * @param fileName to generate data.
     * @param lines to generate.
     * @throws IOException or not.
     */
    public static void generate(String fileName, int lines) throws IOException {
        File fout = new File(fileName);
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 0; i < lines; i++) {
            bw.write(UUID.randomUUID().toString());
            bw.newLine();
        }

        bw.close();
    }
}
