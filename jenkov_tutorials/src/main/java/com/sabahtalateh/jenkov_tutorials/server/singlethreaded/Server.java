package com.sabahtalateh.jenkov_tutorials.server.singlethreaded;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server.
 */
public class Server implements Runnable {

    protected final int serverPort;
    protected ServerSocket serverSocket;
    protected boolean isStopped;
    protected Thread runningThread;

    /**
     * @param serverPort port the server will be listening.
     */
    public Server(int serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * Run.
     */
    @Override
    public void run() {
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();

        while (!isStopped()) {
            Socket clientSocket;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                if (isStopped) {
                    System.out.println("Server stopped");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            try {
                processRequest(clientSocket);
            } catch (IOException e) {
                // log exception.
            }
        }

        System.out.println("Server stopped.");
    }

    /**
     * Stop.
     */
    public void stop() {
        this.isStopped = true;

        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Cannot close server socket on port %s", serverPort), e);
        }
    }

    /**
     * @param clientSocket client socket.
     * @throws IOException exception.
     */
    private void processRequest(Socket clientSocket) throws IOException {
        InputStream input = clientSocket.getInputStream();
        OutputStream output = clientSocket.getOutputStream();
        long time = System.currentTimeMillis();

        String body = "<html><body>"
                + "Singlethreaded Server time:" + time
                + "</body></html>";

        String header = String.format("HTTP/1.1 200 OK\n"
                + "Content-Type: text/html; charset=UTF-8\n"
                + "Content-Length: %s\n\n", body.length());

        output.write((header + body).getBytes());
        output.close();
        input.close();
        System.out.printf("Request processed %s%n", time);
    }

    /**
     * Open server socket.
     */
    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Cannot open port %s", serverPort), e);
        }
    }

    /**
     * @return is stopped.
     */
    private boolean isStopped() {
        return isStopped;
    }
}
