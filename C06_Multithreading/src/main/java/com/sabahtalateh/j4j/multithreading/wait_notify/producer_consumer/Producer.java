package com.sabahtalateh.j4j.multithreading.wait_notify.producer_consumer;

import static java.lang.Math.random;
import static java.lang.String.format;

/**
 * Producer.
 */
public class Producer implements Runnable {

    private final AsyncBuffer<String> asyncBuffer;

    private final String terminationWord;

    /**
     * @param asyncBuffer     buffer.
     * @param terminationWord termination word.
     */
    public Producer(AsyncBuffer<String> asyncBuffer, String terminationWord) {
        this.asyncBuffer = asyncBuffer;
        this.terminationWord = terminationWord;
    }

    /**
     * Run.
     */
    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                Thread.sleep((long) (1000 * random()));
                asyncBuffer.put(format("Message #%d", i));
            }
            asyncBuffer.put(terminationWord);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
