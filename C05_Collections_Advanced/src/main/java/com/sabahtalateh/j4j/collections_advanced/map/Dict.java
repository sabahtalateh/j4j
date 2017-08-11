package com.sabahtalateh.j4j.collections_advanced.map;

import java.util.Iterator;

import static java.lang.Math.abs;

/**
 * @param <K> Key type.
 * @param <V> Value type.
 */
public class Dict<K, V> {

    private static final int INITIAL_CAPACITY = 16;

    private Object[] elements;

    /**
     * Default constructor.
     */
    Dict() {
        this(INITIAL_CAPACITY);
    }

    /**
     * @param initialCapacity capacity.
     */
    Dict(int initialCapacity) {
        this.elements = new Object[initialCapacity < 1 ? 1 : initialCapacity];
    }

    /**
     * @param key   key.
     * @param value value.
     * @return false if element with same key is already existed in dict.
     */
    public boolean insert(K key, V value) {
        boolean exists = false;

        int hash = key.hashCode();
        int bucket = abs(hash % this.elements.length);

        while (this.elements[bucket] != null && ((Entry<V>) this.elements[bucket]).hash != hash) {
            this.resize();
            this.relocateElements();
            bucket = abs(hash % this.elements.length);
        }

        if (this.elements[bucket] != null && ((Entry<V>) this.elements[bucket]).hash == hash) {
            exists = true;
        } else {
            this.elements[bucket] = new Entry<V>(hash, value);
        }

        return !exists;
    }

    /**
     * @param key key.
     * @return true if element was deleted, false if element is not presented in dict.
     */
    public boolean delete(K key) {
        boolean deleted = false;

        int hash = key.hashCode();
        int bucket = abs(hash % this.elements.length);

        if (this.elements[bucket] != null) {
            this.elements[bucket] = null;
            deleted = true;
        }

        return deleted;
    }

    /**
     * @param key key.
     * @return value.
     */
    public V get(K key) {
        Entry<V> entry = (Entry<V>) this.elements[abs(key.hashCode() % this.elements.length)];
        return entry != null && entry.hash == key.hashCode() ? entry.value : null;
    }

    /**
     * @return iterator.
     */
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            int position = 0;

            /**
             * @return has next.
             */
            @Override
            public boolean hasNext() {
                return this.getNextPosition() != -1;
            }

            /**
             * @return next.
             */
            @Override
            public V next() {
                this.position = this.getNextPosition();
                return ((Entry<V>) Dict.this.elements[this.position++]).value;
            }

            /**
             * @return next not null element position.
             */
            private int getNextPosition() {
                int nextPosition = -1;
                for (int i = this.position; i < Dict.this.elements.length; i++) {
                    if (Dict.this.elements[i] != null) {
                        nextPosition = i;
                        break;
                    }
                }
                return nextPosition;
            }
        };
    }

    /**
     * Resize storage.
     */
    private void resize() {
        Object[] newElements = new Object[this.elements.length * 2];
        System.arraycopy(this.elements, 0, newElements, 0, this.elements.length);
        this.elements = newElements;
    }

    /**
     * Relocate elements in storage.
     */
    private void relocateElements() {
        for (int i = 0; i != this.elements.length / 2; i++) {
            Entry<V> element = (Entry<V>) this.elements[i];
            if (element != null) {
                int newBucket = abs(element.hash % this.elements.length);
                if (newBucket != i) {
                    this.elements[newBucket] = element;
                    this.elements[i] = null;
                }
            }
        }
    }

    /**
     * @param <V> Type.
     */
    private static class Entry<V> {
        private int hash;
        private V value;

        /**
         * @param hash  hash.
         * @param value value.
         */
        Entry(int hash, V value) {
            this.hash = hash;
            this.value = value;
        }
    }
}
