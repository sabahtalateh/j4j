package com.sabahtalateh.j4j.multithreading.oracle.guarded;

/**
 * GuardSimpleDemo.
 */
public class GuardSimpleDemo {

    /**
     * @param args args.
     */
    public static void main(String[] args) {
        Guard guard = new Guard();
        new Thread(() -> {
            for (String s = guard.get(); !s.equals("DONE"); s = guard.get()) {
                System.out.println(s);
            }
            System.out.println("DONE");
        }).start();
        new Thread(() -> {

            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                guard.set("Message " + i);
            }
            guard.set("DONE");
        }).start();
    }


    /**
     * Guard.
     */
    private static class Guard {

        String string = null;

        /**
         * @return value.
         */
        synchronized String get() {
            while (this.string == null) {
                System.out.printf("Wait for string to be set..%n");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String s = this.string;
            this.string = null;
            this.notifyAll();
            return s;
        }

        /**
         * @param string string.
         */
        synchronized void set(String string) {
            while (this.string != null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.notifyAll();
            this.string = string;
        }
    }
}
