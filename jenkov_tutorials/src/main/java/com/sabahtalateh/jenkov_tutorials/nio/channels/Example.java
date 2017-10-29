package com.sabahtalateh.jenkov_tutorials.nio.channels;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ClientExample.
 */
public class Example {
    /**
     * @param args args.
     * @throws IOException exception.
     */
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("jenkov_tutorials/in/channel_example.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(16);

        int bytesRead = channel.read(buffer);

        while (bytesRead != -1) {
            System.out.printf("Read %s%n", bytesRead);

            buffer.flip();

            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }

            System.out.println();
            buffer.clear();
            bytesRead = channel.read(buffer);
        }

        file.close();
    }
}
