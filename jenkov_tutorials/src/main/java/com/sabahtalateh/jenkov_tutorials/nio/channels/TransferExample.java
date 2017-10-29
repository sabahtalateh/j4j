package com.sabahtalateh.jenkov_tutorials.nio.channels;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * TransferExample.
 */
public class TransferExample {
    /**
     * @param args args.
     * @throws IOException exception.
     */
    public static void main(String[] args) throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("jenkov_tutorials/in/channel_example.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("jenkov_tutorials/out/channel_example.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        toChannel.transferFrom(fromChannel, 0, fromChannel.size());

        fromChannel.close();
        toChannel.close();
    }
}
