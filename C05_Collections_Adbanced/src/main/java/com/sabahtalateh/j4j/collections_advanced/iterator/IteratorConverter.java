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
        this.setInnerIteratorToFirstNotEmptyValue();
        return this.inner != null && this.inner.hasNext();
    }

    /**
     * @return next.
     */
    @Override
    public Integer next() {
        this.setInnerIteratorToFirstNotEmptyValue();
        return this.inner == null ? null : this.inner.next();
    }

    /**
     * Set inner iterator to first not empty value.
     */
    private void setInnerIteratorToFirstNotEmptyValue() {
        if (this.inner == null && this.iterator.hasNext()) {
            this.inner = this.iterator.next();
        }

        if (this.inner != null) {
            while (!this.inner.hasNext() && this.iterator.hasNext()) {
                if (this.iterator.hasNext()) {
                    this.inner = this.iterator.next();
                }
            }
        }
    }
}


