package com.sabahtalateh.j4j.multithreading.jenkov.blocking_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayedQueueExample.
 */
public class DelayedQueueExample {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        BlockingQueue<Message> queue = new DelayQueue<>();

        // Producer.
        new Thread(() -> {
            try {
                queue.put(new Message("Message 1", 1000));
                queue.put(new Message("Message 2", 2000));
                queue.put(new Message("Message 3", 2500));
                queue.put(new Message("END", 5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Consumer.
        new Thread(() -> {
            try {
                Thread.sleep(200);
                for (Message m = queue.take(); !m.getText().equals("END"); m = queue.take()) {
                    System.out.println(m.getText());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    /**
     * Message.
     */
    private static class Message implements Delayed {

        private final String text;

        private final long startTime;

        /**
         * @param text  text.
         * @param delay delay.
         */
        Message(String text, long delay) {
            this.text = text;
            this.startTime = System.currentTimeMillis() + delay;
        }

        /**
         * @return text.
         */
        public String getText() {
            return text;
        }

        /**
         * @param unit unit.
         * @return delay.
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(startTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        /**
         * @param o to compare.
         * @return comparison result.
         */
        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }
    }
}
