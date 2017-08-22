package com.sabahtalateh.j4j.multithreading.oracle.lock_objects;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * SafeLock.
 */
public class SafeLock {

    /**
     * @param args args.
     */
    public static void main(String[] args) {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");

        new Thread(new BowLoop(alphonse, gaston)).start();
        new Thread(new BowLoop(gaston, alphonse)).start();
    }

    /**
     * Friend.
     */
    private static class Friend {
        private final String name;
        private final Lock lock = new ReentrantLock();

        /**
         * @param name name.
         */
        Friend(String name) {
            this.name = name;
        }

        /**
         * @return name.
         */
        String getName() {
            return name;
        }

        /**
         * @param bower bower.
         * @return locked flag.
         */
        boolean impendingBow(Friend bower) {
            Boolean myLock = false;
            Boolean yourLock = false;

            try {
                myLock = lock.tryLock();
                yourLock = bower.lock.tryLock();
            } finally {
                if (!(myLock && yourLock)) {
                    if (myLock) {
                        lock.unlock();
                    }

                    if (yourLock) {
                        bower.lock.unlock();
                    }
                }
            }

            return myLock && yourLock;
        }

        /**
         * @param bower bower.
         */
        void bow(Friend bower) {
            if (impendingBow(bower)) {
                try {
                    System.out.printf("%s: %s has bowed to me!%n", this.name, bower.getName());
                    bower.bowBack(this);
                } finally {
                    lock.unlock();
                    bower.lock.unlock();
                }
            } else {
                System.out.printf("%s: %s started to bow me, but saw that I already bowed to him.%n", this.name, bower.getName());
            }
        }

        /**
         * @param bower bower.
         */
        void bowBack(Friend bower) {
            System.out.printf("%s: %s has bowed back to me!%n", this.name, bower.getName());
        }
    }

    /**
     * BowLoop.
     */
    private static class BowLoop implements Runnable {
        private final Friend bower;
        private final Friend bowee;

        /**
         * @param bower bower.
         * @param bowee bowee.
         */
        private BowLoop(Friend bower, Friend bowee) {
            this.bower = bower;
            this.bowee = bowee;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            Random random = new Random();

            while (true) {
                System.out.printf("---%n");
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bowee.bow(bower);
            }
        }
    }
}
