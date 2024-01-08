package ru.spbstu.telematics.java.lab2;

import java.util.function.Consumer;

/**
 * Implementing this interface allows an object to be the target of the "for-each loop" statement
 * @param <T> the type of elements returned by the iterator
 */
public interface MyIterable<T> {
    /**
     * Performs the given action for each element of the MyIterable until all elements have been processed or the action throws an exception.
     * @param action the action
     */
    default void forEach(Consumer<? super T> action) {
        for (MyIterator<T> i = iterator(); i.hasNext(); ) {
            action.accept(i.next());
        }
    }

    /**
     * Return an iterator for the MyIterable object
     * @return an iterator
     */
    MyIterator<T> iterator();

    /**
     * Get the size of current Collection
     *
     * @return size of the specified collection
     */
    int size();

    /**
     * Check if the item is in the specified collection
     *
     * @param item the item to check
     * @return true if the collection has the item
     */
    boolean contains(Object item);

    /**
     * Add new item to the collection
     *
     * @param item the item to add
     */
    void add(T item) throws NullPointerException;

    /**
     * Remove an item in MyIterable.
     * @param item the item to be removed
     * @return true if the MyIterable object changes.
     */
    boolean remove(Object item);
}
