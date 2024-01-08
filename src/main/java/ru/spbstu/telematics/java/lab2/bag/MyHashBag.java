package ru.spbstu.telematics.java.lab2.bag;

import ru.spbstu.telematics.java.lab2.MyIterable;
import ru.spbstu.telematics.java.lab2.MyIterator;
import ru.spbstu.telematics.java.lab2.map.MyHashMap;
import ru.spbstu.telematics.java.lab2.list.arraylist.MyArrayList;
import ru.spbstu.telematics.java.lab2.map.MyMap;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * HashBag implementation
 * Null is not counted
 *
 * @param <T> type parameter of HashBag
 */
public class MyHashBag<T> implements MyBag<T> {
    transient MyMap<T, Integer> listItem;
    int size = 0;

    String repr = "";

    /**
     * Create an empty Bag
     */
    public MyHashBag() {
        listItem = new MyHashMap<>();
    }

    /**
     * Create a Bag from items in a MyIterable object
     * @param collection the object containing items
     * @throws NullPointerException if the input object is null
     */
    public MyHashBag(MyIterable<? extends T> collection) throws NullPointerException {
        if (collection == null)
            throw new NullPointerException();

        listItem = new MyHashMap<>();
        collection.forEach(this::add);
    }
    /**
     * Create a Bag from items in a Collection object
     * @param collection the object containing items
     * @throws NullPointerException if the input object is null
     */
    public MyHashBag(Collection<? extends T> collection) throws NullPointerException {
        if (collection == null)
            throw new NullPointerException();

        listItem = new MyHashMap<>();
        collection.forEach(this::add);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MyIterator<T> iterator() {
        return new MyIterator<T>() {
            final MyArrayList<MyHashMap.MyEntry<T, Integer>> items = listItem.entryArray();
            final MyIterator<MyHashMap.MyEntry<T, Integer>> itemIterator = items.iterator();
            MyHashMap.MyEntry<T, Integer> currentItem;
            int currentPointedCount;

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean hasNext() {
                return currentPointedCount > 0 || itemIterator.hasNext();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public T next() throws NoSuchElementException {
                if (!hasNext())
                    throw new NoSuchElementException();
                if (currentPointedCount == 0) {
                    currentItem = itemIterator.next();
                    currentPointedCount = currentItem.getValue();
                }
                currentPointedCount--;
                return currentItem.getKey();
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Object item) {
        return listItem.containsKey(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return listItem.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(T element) {
        listItem.put(element, getCount(element) + 1);
        size++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object element) {
        size -= getCount(element);
        return listItem.remove(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object element, int nCopies) {
        if (nCopies == 0) return false;
        int count = getCount(element);
        if (count < nCopies)
            return false;
        else if (count > nCopies) {
            listItem.put((T) element, count - nCopies);
        } else
            listItem.remove(element);
        size -= count;
        return true;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount(Object element) {
        Integer count = listItem.get(element);
        if (count == null)
            return 0;
        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return listItem.entryArray().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        listItem.clear();
        size = 0;
    }

}