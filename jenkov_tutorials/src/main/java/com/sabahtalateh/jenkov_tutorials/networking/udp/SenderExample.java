package com.sabahtalateh.jenkov_tutorials.networking.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * SenderExample.
 */
public class SenderExample {
    /**
     * To see the result run.
     *
     * nc -l -u -p 4444
     *
     * @param args args.
     * @throws IOException exception.
     */
    public static void main(String[] args) throws IOException {
        byte[] buffer = "HELLO UDP!".getBytes();
        InetAddress receiverAddress = InetAddress.getByName("localhost");

        DatagramSocket datagramSocket = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, receiverAddress, 4444);
        datagramSocket.send(packet);
        datagramSocket.close();
    }
}
