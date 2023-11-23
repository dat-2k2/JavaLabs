package ru.spbstu.telematics.java.lab2.bag;
import ru.spbstu.telematics.java.lab2.MyIterable;
import ru.spbstu.telematics.java.lab2.arraylist.MyArrayList;


public interface MyBagInterface<T> extends MyIterable<T> {
    /**
     * Get number of different items in Bag
     * @return the number of different items in Bag
     */
    int howManyItems();
    /**
     * Check if the list is empty
     * @return true if the list is empty
     */
    boolean isEmpty();

    /**
     * Remove the first occurrence of object E
     * @param element the element to remove
     * @return true if the element exists in the bag
     */
    boolean remove(T element);

    /**
     * Get the number of occurrence of the specified element in the bag
     * @param element the element to get
     * @return number of its occurrence in the bag, 0 if there isn't
     */
    Integer getOccur(T element);

    /**
     * Get a list of items in the bag
     * @return list of items in the bag
     */
    MyArrayList<T> items();

}
