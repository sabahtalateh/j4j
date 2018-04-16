package com.sabahtalateh.j4j.jdbc.test_task;

/**
 * StoreInterface.
 */
interface StoreInterface {
    /**
     * @param e event to add to store.
     * @return boolean.
     */
    boolean add(Event e);

    /**
     * @return how many events are in store.
     */
    long size();
}
