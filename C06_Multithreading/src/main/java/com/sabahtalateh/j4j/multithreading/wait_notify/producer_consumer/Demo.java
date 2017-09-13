package com.sabahtalateh.j4j.multithreading.wait_notify.producer_consumer;

/**
 * Demo.
 */
public class Demo {
    /**
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        final String terminationWord = "END";
        AsyncBuffer<String> asyncBuffer = new AsyncBuffer<>();
        Thread consumer = new Thread(new Consumer(asyncBuffer, terminationWord));
        Thread producer = new Thread(new Producer(asyncBuffer, terminationWord));
        consumer.start();
        producer.start();
    }
}
