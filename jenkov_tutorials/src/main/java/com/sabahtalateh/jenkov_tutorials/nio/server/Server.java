package com.sabahtalateh.jenkov_tutorials.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Server.
 */
public class Server {
    /**
     * @param args args.
     * @throws IOException exception.
     */
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 3456));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey selectedKey = iterator.next();

                // Server accepts a connection.
                if (selectedKey.isAcceptable()) {
                    SocketChannel client = serverSocketChannel.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                }

                if (selectedKey.isWritable()) {
                    System.out.println("Writable");
                    SocketChannel client = (SocketChannel) selectedKey.channel();

                    long time = System.currentTimeMillis();

                    String body = "<html><body>"
                            + "Java NIO Server: "
                            + time
                            + "</body></html>";

                    String header = String.format("HTTP/1.1 200 OK\n"
                            + "Content-Type: text/html; charset=UTF-8\n"
                            + "Content-Length: %s\n\n", body.length());

                    buffer.put((header + body).getBytes());

                    buffer.flip();
                    client.write(buffer);
                    buffer.clear();
                    client.close();
                }

                iterator.remove();
            }
        }
    }
}
