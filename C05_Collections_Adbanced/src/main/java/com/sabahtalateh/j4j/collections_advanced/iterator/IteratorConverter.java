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

    private Iterator<Integer> current;

    /**
     * @param iterator iterator.
     */
    CustomIterator(Iterator<Iterator<Integer>> iterator) {
        this.iterator = iterator;
        if (iterator.hasNext()) {
            current = iterator.next();
        }
    }

    /**
     * @return has next.
     */
    @Override
    public boolean hasNext() {
        if (this.current.hasNext()) {
            return true;
        }

        while (iterator.hasNext()) {
            this.current = iterator.next();
            return this.hasNext();
        }

        return false;
    }

    /**
     * @return next.
     */
    @Override
    public Integer next() {
        if (this.current.hasNext()) {
            return this.current.next();
        }

        while (this.iterator.hasNext()) {
            this.current = this.iterator.next();
            return this.next();
        }

        return null;
    }
}


