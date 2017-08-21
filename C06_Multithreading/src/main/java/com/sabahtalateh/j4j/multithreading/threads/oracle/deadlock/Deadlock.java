package com.sabahtalateh.j4j.multithreading.threads.oracle.deadlock;

/**
 * Deadlock.
 */
public class Deadlock {

    /**
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");
        new Thread(() -> alphonse.bow(gaston)).start();
        new Thread(() -> gaston.bow(alphonse)).start();

        Thread.sleep(100);

        System.out.println(alphonse.whoWaitToBowBack());
        System.out.println(gaston.whoWaitToBowBack());
    }

    /**
     * Friend.
     */
    private static class Friend {

        private final String name;

        private Friend bowedToMe;

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
         */
        synchronized void bow(Friend bower) {
            this.bowedToMe = bower;
            System.out.printf("%s: %s has bowed to me!%n", this.name, bower.getName());
            bower.bowBack(this);
        }

        /**
         * @param bower bower.
         */
        synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s has bowed back to me!%n", this.name, bower.getName());
        }

        /**
         * @return friend.
         */
        Friend whoWaitToBowBack() {
            return bowedToMe;
        }

        /**
         * @return string.
         */
        @Override
        public String toString() {
            return "Friend{name='" + name + '\'' + ", bowedToMe=" + bowedToMe.getName() + '}';
        }
    }
}
