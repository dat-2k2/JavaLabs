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
     * Return true if the list contains the specified object
     * @param o the object to find
     * @return true if o is found
     */
    boolean contains(Object o);

    /**
     * Add a new E element to the list
     * @param element the adding object of type E
     */
    void add(E element);

    /**
     * Remove the first occurrence of object E
     * @param element the element to remove
     */
    void remove(E element);

    /**
     * Get the element at position index
     * @param index of the element
     * @return the element at position index
     */
    E get(int index);

    /**
     * Replaces the element at the specified position in the list with the specified element
     * @param index index of the element to replace
     * @param element the element to be stored at the specified position
     * @return the element previously at the specified position
     */
    E set(int index, E element);

    /**
     * Clear the whole list
     */
    void clear();

    /**
     * Compare to the specified object with this list for equality.
     * Return true iff the specified object is also a list, both lists have the same size, and all corresponding pairs of elements in the two lists are equal.
     * @param o the object to be compared for the equality with this list
     * @return true if the specified object is equal to this list
     */
    boolean equals(Object o);
}
