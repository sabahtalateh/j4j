package com.sabahtalateh.jenkov_tutorials.networking.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * ReceiverExample.
 */
public class ReceiverExample {
    /**
     * Run this server.
     *
     * Then run
     * echo "HI" | nc -uc localhost 4444
     *
     * @param args args.
     * @throws IOException exception.
     */
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(4444);

        byte[] buffer = new byte[10];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        socket.receive(packet);

        for (byte b : packet.getData()) {
            System.out.print((char) b);
        }
        System.out.println();
    }
}
