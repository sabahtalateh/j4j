package com.sabahtalateh.j4j.multithreading.wait_notify.j4j_examples;

/**
 * Example.
 */
public class Example {
    private final Object monitor = new Object();
    private Boolean readyToSend = false;

    /**
     * @throws InterruptedException exception.
     */
    public void send() throws InterruptedException {
        synchronized (monitor) {
            System.out.println("Waiting for data.");
            while (!readyToSend) {
                monitor.wait();
            }
            System.out.println("Sending data.");
        }
    }

    /**
     * @throws InterruptedException exception.
     */
    public void prepare() throws InterruptedException {
        synchronized (monitor) {
            Thread.sleep((long) (Math.random() * 1000));
            System.out.println("Data prepared.");
            this.readyToSend = true;
            monitor.notifyAll();
        }
    }

    /**
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        Example example = new Example();
        new Thread(new Sender(example)).start();
        new Thread(new Preparer(example)).start();
    }


    /**
     * Preparer.
     */
    private static class Preparer implements Runnable {

        private final Example example;

        /**
         * @param example example.
         */
        Preparer(Example example) {
            this.example = example;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            try {
                example.prepare();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sender.
     */
    private static class Sender implements Runnable {

        private final Example example;

        /**
         * @param example example.
         */
        Sender(Example example) {
            this.example = example;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            try {
                example.send();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
