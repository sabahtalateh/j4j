package com.sabahtalateh.j4j.multithreading.jenkov.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ArrayBlockingQueueExample.
 */
public class ArrayBlockingQueueExample {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1024);

        // Producer.
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep((int) (Math.random() * 1000));
                    queue.put("Message " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                queue.put("END");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Consumer.
        new Thread(() -> {
            try {
                for (String s = queue.take(); !s.equals("END"); s = queue.take()) {
                    System.out.println(s);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
