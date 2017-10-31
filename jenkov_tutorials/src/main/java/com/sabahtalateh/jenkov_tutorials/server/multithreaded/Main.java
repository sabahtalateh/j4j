package com.sabahtalateh.jenkov_tutorials.server.multithreaded;

/**
 * Main.
 */
public class Main {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        Server server = new Server(3456);
        new Thread(server).start();

        try {
            Thread.sleep(100 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stopping server.");
        server.stop();
    }
}
