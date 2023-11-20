package ru.spbstu.telematics.java.lab2.arraylist;

import ru.spbstu.telematics.java.lab2.MyIterable;

public interface MyArrayListInterface<T> extends MyIterable<T> {
    /**
     * Check if the specified Array List is empty
     * @return true if the list is empty
     */
    boolean isEmpty();

    /**
     * Change the capacity of array
     * @param newCapacity the new capacity
     */
    void ensureCapacity(int newCapacity);

    /**
     * Increase capacity by 1
     */
    void trimToSize();

    /**
     * Remove the item
     * @param index index of the item to remove
     * @return the removed item
     */
    T remove(int index) throws IndexOutOfBoundsException;

    /**
     * Set the item at position index with a new value
     * @param index the position of the specified item
     * @param newValue the new value to set
     * @return the old value
     * @throws IndexOutOfBoundsException if the position is out of the array
     */
    T set(int index, T newValue) throws IndexOutOfBoundsException;

    /**
     * Get the item at position index
     * @param index the position
     * @return the item at position index
     * @throws IndexOutOfBoundsException if the position is out of the array
     */
    T get(int index) throws IndexOutOfBoundsException;

    /**
     * Add a new item to the position index of the array
     * @param index the position to add the new item
     * @param item the item to add
     * @throws IndexOutOfBoundsException if the position is out of the array
     */
    void add(int index, T item) throws IndexOutOfBoundsException;

    /**
     * Find the index of the given element.
     * @param element the element to find
     * @return the index of the given element.  If the list does not contain the element, return -1;
     */
    int indexOf(Object element);

}
