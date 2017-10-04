package com.sabahtalateh.j4j.multithreading.jenkov.wait_notify;

/**
 * WaitNotify.
 */
public class WaitNotify {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        new WaitNotify().run();
    }

    /**
     * Run.
     */
    public void run() {
        WaitNotifyObject wno = new WaitNotifyObject();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wno.doNotify();
        }).start();
        new Thread(wno::doWait).start();
    }

    /**
     * WaitNotifyObject.
     */
    public static class WaitNotifyObject {
        final Object monitor = new Object();
        boolean ready = false;

        /**
         * Wait.
         */
        void doWait() {
            synchronized (monitor) {
                while (!ready) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ready = false;
            }
        }

        /**
         * Notify.
         */
        void doNotify() {
            synchronized (monitor) {
                ready = true;
                monitor.notify();
            }
        }
    }

}
