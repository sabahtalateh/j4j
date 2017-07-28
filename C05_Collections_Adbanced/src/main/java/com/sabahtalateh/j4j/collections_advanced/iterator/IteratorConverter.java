package com.sabahtalateh.j4j.collections_advanced.iterator;

import java.util.Iterator;

/**
 * IteratorConverter.
 */
public class IteratorConverter {
    /**
     * @param iterator iterator of iterators.
     * @return iterator.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> iterator) {
        return new CustomIterator(iterator);
    }
}

/**
 * CustomIterator.
 */
class CustomIterator implements Iterator<Integer> {

    private final Iterator<Iterator<Integer>> iterator;

    private Iterator<Integer> inner;

    /**
     * @param iterator iterator.
     */
    CustomIterator(Iterator<Iterator<Integer>> iterator) {
        this.iterator = iterator;
    }

    /**
     * @return has next.
     */
    @Override
    public boolean hasNext() {
        if (this.inner == null && !this.iterator.hasNext()) {
            return false;
        }

        if (this.inner == null && this.iterator.hasNext()) {
            inner = iterator.next();
        }

        if (this.inner.hasNext()) {
            return true;
        }

        if (iterator.hasNext()) {
            this.inner = iterator.next();
            return this.hasNext();
        }

        return false;
    }

    /**
     * @return next.
     */
    @Override
    public Integer next() {
        if (this.inner == null && !this.iterator.hasNext()) {
            return null;
        }

        if (this.inner == null && this.iterator.hasNext()) {
            inner = iterator.next();
        }

        if (this.inner.hasNext()) {
            return this.inner.next();
        }

        if (this.iterator.hasNext()) {
            this.inner = this.iterator.next();
            return this.next();
        }

        return null;
    }
}


