package com.sabahtalateh.j4j.multithreading.threads.oracle.guarded;

/**
 * Drop.
 */
class Drop {

    // Message from producer.
    private String message;

    // True if consumer should wait for producer to put a message.
    // False if producer should wait for consumer to retrieve a message.
    private boolean empty = true;

    /**
     * @return string.
     */
    synchronized String take() {
        while (this.empty) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Toggle status.
        this.empty = true;
        // Notify producer that status has changed.
        this.notifyAll();
        return this.message;
    }

    /**
     * @param message message.
     */
    synchronized void put(String message) {
        while (!this.empty) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Toggle status.
        this.empty = false;
        // Put message.
        this.message = message;
        // Notify consumer that message has put.
        this.notifyAll();
    }
}
