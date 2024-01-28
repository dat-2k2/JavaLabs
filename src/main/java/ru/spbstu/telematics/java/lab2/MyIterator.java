package ru.spbstu.telematics.java.lab2;

import java.util.NoSuchElementException;


/**
 * An iterator over a collection.
 *
 * @param <T> the type of elements returned by this iterator
 */
public interface MyIterator<T> {
    /**
     * Check if there is any next one of the specified item
     *
     * @return true if there is
     */
    boolean hasNext();

    /**
     * Iterate to the next item of specified iterator
     *
     * @return the value of BEFORE MOVING element
     */
    T next() throws NoSuchElementException;

}
