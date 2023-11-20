package ru.spbstu.telematics.java.lab2;

// basic requirements for this lab
// the remove and get methods are declared in children classes
public interface Lab2Interface<T> {
    /**
     * Get the size of current Collection
     * @return size of the specified collection
     */
    int size();

    /**
     * Check if the item is in the specified collection
     * @param item the item to check
     * @return true if the collection has the item
     */
    boolean contains(Object item);

    /**
     * Add new item to the collection
     * @param item the item to add
     */
    void add(T item) throws NullPointerException;

}
