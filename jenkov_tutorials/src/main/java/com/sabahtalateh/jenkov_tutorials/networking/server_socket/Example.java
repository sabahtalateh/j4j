package com.sabahtalateh.jenkov_tutorials.networking.server_socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ClientExample.
 */
public class Example {
    /**
     * To see response you can run.
     *
     * nc 127.0.0.1 4444
     *
     * Or from browser or curl.
     *
     *
     * @param args args.
     * @throws IOException exception.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4444);

        while (true) {
            String response = "HTTP/1.1 200 OK\n"
                    + "Content-Type: text/html;\n"
                    + "\n"
                    + "<HTML>"
                    + "<H1>HELLO!</H1>"
                    + "</HTML>";
            Socket clientSocket = serverSocket.accept();
            clientSocket.getOutputStream().write(response.getBytes());
            clientSocket.close();
        }
    }
}
