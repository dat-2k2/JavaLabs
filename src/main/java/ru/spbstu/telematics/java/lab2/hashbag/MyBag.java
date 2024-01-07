package ru.spbstu.telematics.java.lab2.hashbag;

import ru.spbstu.telematics.java.lab2.MyIterable;

/**
 * {@inheritDoc}
 * Defines a collection that counts the number of times an object appears in the collection.
 * @param <T>
 */

public interface MyBag<T> extends MyIterable<T> {
    /**
     * Check if the list is empty
     *
     * @return true if the list is empty
     */
    boolean isEmpty();

    /**
     * Remove all copies of object E
     *
     * @param object the object to remove
     * @return true if the element exists in the bag
     */
    boolean remove(Object object);

    /**
     * Removes a specified number of copies of an object from the bag.
     *
     * @param element the object to remove
     * @param nCopies the number of copies to remove
     * @return true if the bag changed
     */
    boolean remove(Object element, int nCopies);

    /**
     * Get the number of occurrence of the specified element in the bag
     *
     * @param element the element to get
     * @return number of its occurrence in the bag, 0 if there isn't
     */
    int getCount(Object element);


    /**
     * Clears the bag by clearing the underlying map.
     */
    void clear();
}
