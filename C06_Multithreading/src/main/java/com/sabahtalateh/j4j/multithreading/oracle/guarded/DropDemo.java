package com.sabahtalateh.j4j.multithreading.oracle.guarded;

/**
 * DropDemo.
 */
public class DropDemo {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        Drop drop = new Drop();
        (new Thread(new Consumer(drop))).start();
        (new Thread(new Producer(drop))).start();
    }
}
