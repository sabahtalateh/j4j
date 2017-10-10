package com.sabahtalateh.j4j.multithreading.jenkov.exchanger;

import java.util.concurrent.Exchanger;

/**
 * ExchangerExample.
 */
public class ExchangerExample {
    /**
     * @param args args.
     */
    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            String myMessage = "SemaphoreVisitor";
            try {
                Thread.sleep(1000);
                String myNewMessage = exchanger.exchange(myMessage);
                System.out.printf("T1: [%s] exchanged to [%s].%n", myMessage, myNewMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            String myMessage = "B";
            try {
                Thread.sleep(1500);
                String myNewMessage = exchanger.exchange(myMessage);
                System.out.printf("T2: [%s] exchanged to [%s].%n", myMessage, myNewMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
