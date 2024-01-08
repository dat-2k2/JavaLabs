package ru.spbstu.telematics.java.lab2.list;

import ru.spbstu.telematics.java.lab2.MyIterable;

public interface MyList<T> extends MyIterable<T> {
    /**
     * Check if the list is empty
     * @return true if the list is empty
     */
    boolean isEmpty();
    /**
     * Get the element at position index
     * @param index of the element
     * @return the element at position index
     * @throws IndexOutOfBoundsException when index is out of range
     */
    T get(int index) throws IndexOutOfBoundsException;


    /**
     * Replaces the element at the specified position in the list with the specified element
     *
     * @param index   index of the element to replace
     * @param element the element to be stored at the specified position
     * @throws IndexOutOfBoundsException when index is out of range
     */
    T set(int index, T element) throws IndexOutOfBoundsException;
    /**
     * Clear the whole list
     */
    void clear();
    /**
     * Add a new item to the position index of the array
     * @param index the position to add the new item
     * @param item the item to add
     * @throws IndexOutOfBoundsException if the position is out of the array
     */
    void add(int index, T item) throws IndexOutOfBoundsException;

    /**
     * Remove the item
     * @param index index of the item to remove
     * @return the removed item
     */
    T remove(int index) throws IndexOutOfBoundsException;

    /**
     * Remove the item
     * @param o the item to remove
     * @return the removed item
     */
    boolean remove(Object o);
    /**
     * Find the index of the given element.
     * @param element the element to find
     * @return the index of the given element.  If the list does not contain the element, return -1;
     */
    int indexOf(Object element);
}
