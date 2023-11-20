package ru.spbstu.telematics.java.lab2.linkedlist;

import ru.spbstu.telematics.java.lab2.MyIterable;

/**
 * Mock the List interface in Java
 * @param <E> generic type E
 */
interface MyLinkedListInterface<E> extends MyIterable<E> {
    /**
     * Check if the list is empty
     * @return true if the list is empty
     */
    boolean isEmpty();

    /**
     * Return the index of the given element
     * @param element
     * @return
     */
    int indexOf(Object element);
    /**
     * Remove the first occurrence of object E
     * @param index the element to remove
     * @throws IndexOutOfBoundsException when index is out of range
     */
    void remove(int index) throws IndexOutOfBoundsException;

    /**
     * Clear the whole list
     */
    void clear();
}
