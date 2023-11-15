package ru.spbstu.telematics.java.lab2;

/**
 * Mock the List interface in Java
 * @param <E> generic type E
 */
interface ListCustom<E>  {

    /**
     * Get the size of the specified list
     * @return size of the list
     */
    int size();

    /**
     * Check if the list is empty
     * @return true if the list is empty
     */
    boolean isEmpty();
    /**
     * Return true if the list contains the specified object
     * @param element the object to find
     * @return true if o is found
     */
    boolean contains(E element);

    int indexOf(E element);

    /**
     * Add a new E element to the list
     * @param element the adding object of type E
     */
    void add(E element);

    /**
     * Remove the first occurrence of object E
     * @param index the element to remove
     * @throws IndexOutOfBoundsException when index is out of range
     */
    void remove(int index) throws IndexOutOfBoundsException;

    /**
     * Get the element at position index
     * @param index of the element
     * @return the element at position index
     * @throws IndexOutOfBoundsException when index is out of range
     */
    E get(int index) throws IndexOutOfBoundsException;

    /**
     * Replaces the element at the specified position in the list with the specified element
     * @param index index of the element to replace
     * @param element the element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException when index is out of range
     */
    E set(int index, E element) throws IndexOutOfBoundsException;

    /**
     * Clear the whole list
     */
    void clear();

}
