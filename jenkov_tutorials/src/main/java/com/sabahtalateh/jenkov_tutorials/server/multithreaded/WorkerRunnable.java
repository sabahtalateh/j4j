package com.sabahtalateh.jenkov_tutorials.server.multithreaded;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * WorkerRunnable.
 */
public class WorkerRunnable implements Runnable {

    protected final Socket clientSocket;
    protected final String serverText;

    /**
     * @param clientSocket client socket.
     * @param serverText   server text.
     */
    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }

    /**
     * Run.
     */
    @Override
    public void run() {
        try {
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();

            long time = System.currentTimeMillis();

            String body = "<html><body>"
                    + "Worker runnable: "
                    + serverText + " - "
                    + time
                    + "</body></html>";

            String header = String.format("HTTP/1.1 200 OK\n"
                    + "Content-Type: text/html; charset=UTF-8\n"
                    + "Content-Length: %s\n\n", body.length());

            output.write((header + body).getBytes());

            output.close();
            input.close();

            System.out.println("Request processed at " + time);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
