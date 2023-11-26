package ru.spbstu.telematics.java.lab2;
import java.util.function.Consumer;

public interface MyIterable<T> {
    default void forEach(Consumer<? super T> action){
        for (MyIterator<T> i = iterator(); i.hasNext();){
            action.accept(i.next());
        }
    }
    MyIterator<T> iterator();

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
