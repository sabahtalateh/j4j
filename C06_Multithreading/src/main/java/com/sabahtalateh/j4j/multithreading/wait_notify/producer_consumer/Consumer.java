package com.sabahtalateh.j4j.multithreading.wait_notify.producer_consumer;

/**
 * Producer.
 */
public class Consumer implements Runnable {

    private final AsyncBuffer<String> asyncBuffer;

    private final String terminationWord;

    /**
     * @param asyncBuffer     buffer.
     * @param terminationWord termination word.
     */
    public Consumer(AsyncBuffer<String> asyncBuffer, String terminationWord) {
        this.asyncBuffer = asyncBuffer;
        this.terminationWord = terminationWord;
    }

    /**
     * Run.
     */
    @Override
    public void run() {
        try {
            for (String s = asyncBuffer.take(); !s.equals(terminationWord); s = asyncBuffer.take()) {
                System.out.printf("RECEIVED: [%s]%n", s);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
