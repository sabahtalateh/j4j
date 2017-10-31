package com.sabahtalateh.jenkov_tutorials.server.multithreaded;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server.
 */
public class Server implements Runnable {

    protected final int serverPort;
    protected ServerSocket serverSocket;
    protected boolean isStopped;
    protected ExecutorService threadPool = Executors.newFixedThreadPool(4);

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
        openServerSocket();

        while (!isStopped()) {
            Socket clientSocket;
            try {
                System.out.println("Ready to accept.");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                if (isStopped) {
                    System.out.println("Server stopped");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            threadPool.execute(new WorkerRunnable(clientSocket, "Multithreaded Server"));
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
