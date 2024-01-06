package ru.spbstu.telematics.java.lab2.hashbag;

import ru.spbstu.telematics.java.lab2.MyIterable;
import ru.spbstu.telematics.java.lab2.MyIterator;
import ru.spbstu.telematics.java.lab2.hashmap.MyHashMap;
import ru.spbstu.telematics.java.lab2.list.arraylist.MyArrayList;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * HashBag implementation
 * Null is not counted
 *
 * @param <T>
 */
public class MyHashBag<T> implements MyBagInterface<T> {
    transient MyHashMap<T, Integer> listItem;
    int size = 0;

    String repr = "";

    public MyHashBag() {
        listItem = new MyHashMap<>();
    }

    public MyHashBag(MyIterable<? extends T> collection) throws NullPointerException {
        if (collection == null)
            throw new NullPointerException();

        listItem = new MyHashMap<>();
        collection.forEach(this::add);
    }

    public MyHashBag(Collection<? extends T> collection) throws NullPointerException {
        if (collection == null)
            throw new NullPointerException();

        listItem = new MyHashMap<>();
        collection.forEach(this::add);
    }


    @Override
    public MyIterator<T> iterator() {
        return new MyIterator<T>() {
            final MyArrayList<MyHashMap.MyEntry<T, Integer>> items = listItem.entryArray();
            final MyIterator<MyHashMap.MyEntry<T, Integer>> itemIterator = items.iterator();
            MyHashMap.MyEntry<T, Integer> currentItem;
            int currentPointedCount;

            @Override
            public boolean hasNext() {
                return currentPointedCount > 0 || itemIterator.hasNext();
            }

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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object item) {
        return listItem.containsKey(item);
    }

    @Override
    public boolean isEmpty() {
        return listItem.isEmpty();
    }

    @Override
    public void add(T element) {
        listItem.put(element, getCount(element) + 1);
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object element) {
        size -= getCount(element);
        return listItem.remove(element);
    }

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

    @Override
    public int getCount(Object element) {
        Integer count = listItem.get(element);
        if (count == null)
            return 0;
        return count;
    }

    @Override
    public String toString() {
        repr = "{";
        listItem.forEach((key, val) -> {
            repr += key + ":" + val + ",";
        });
        repr += "}";
        return repr;
    }

    @Override
    public void clear() {
        listItem.clear();
        size = 0;
    }

}