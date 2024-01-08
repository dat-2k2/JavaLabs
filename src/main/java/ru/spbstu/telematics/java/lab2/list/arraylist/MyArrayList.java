package ru.spbstu.telematics.java.lab2.list.arraylist;

import ru.spbstu.telematics.java.lab2.MyIterable;
import ru.spbstu.telematics.java.lab2.MyIterator;
import ru.spbstu.telematics.java.lab2.list.MyList;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation for List as a continuous array
 * @param <T> type parameter for objects in that array
 */
public class MyArrayList<T> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    int sizeArray;
    int capacityArray;
    //prevent serialization
    transient Object[] itemArray;

    //These constructors ensure that every object in the array is an instance of T

    /**
     * Default constructor for MyArrayList. Create an array with default capacity and size 0.
     */
    public MyArrayList() {
        sizeArray = 0;
        capacityArray = 10;
        itemArray = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Construct an array with the specified capacity
     * @param cap the specified capacity
     * @throws IllegalArgumentException if capacity smaller than 0
     */
    public MyArrayList(int cap) throws IllegalArgumentException {
        if (cap < 0)
            throw new IllegalArgumentException();
        sizeArray = 0;
        capacityArray = cap;
        itemArray = new Object[cap];
    }

    /**
     * Construct an array with data from another MyIterable object
     * @param collection the object to construct array
     */
    public MyArrayList(MyIterable<? extends T> collection) {
        sizeArray = collection.size();
        capacityArray = collection.size();
        itemArray = new Object[capacityArray];
        MyIterator<? extends T> it = collection.iterator();
        for (int i = 0; i < sizeArray; i++) {
            itemArray[i] = it.next();
        }
    }

    /**
     * Construct an array with data from another Collection object
     * @param collection the object to construct array
     */
    public MyArrayList(Collection<? extends T> collection) {
        sizeArray = collection.size();
        capacityArray = collection.size();
        itemArray = new Object[capacityArray];
        Iterator<? extends T> it = collection.iterator();
        for (int i = 0; i < sizeArray; i++) {
            itemArray[i] = it.next();
        }
    }

    //------------------------------------------------------------------------------
    @Override
    public MyIterator<T> iterator() {
        return new MyIterator<T>() {
            int index;

            @Override
            public boolean hasNext() {
                return index < sizeArray;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() throws NoSuchElementException {
                if (hasNext()) {
                    return (T) itemArray[index++];
                }
                throw new NoSuchElementException("End of the ArrayList");
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return sizeArray;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Object item) {
        return indexOf(item) > -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(T item) throws NullPointerException{
        if (item == null)
            throw new NullPointerException();
        add(sizeArray, item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return sizeArray == 0;
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < capacityArray)
            return;

        Object[] oldArray = itemArray;
        itemArray = new Object[newCapacity];

        capacityArray = newCapacity;
        // copy the old data to the new array;
        System.arraycopy(oldArray, 0, itemArray, 0, sizeArray);
    }

    public void trimToSize() {
        ensureCapacity(sizeArray + 1);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index >= sizeArray)
            throw new IndexOutOfBoundsException();

        Object removeItem = itemArray[index];
        //left-shift the data after deleted element
        for (int i = index; i < sizeArray - 1; i++) {
            itemArray[i] = itemArray[i + 1];
        }
        sizeArray--;
        return (T) removeItem;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index > -1) {
            remove(index);
            return true;
        } else
            return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T newValue) throws IndexOutOfBoundsException {
        if (index >= size())
            throw new IndexOutOfBoundsException();

        T oldValue = (T) itemArray[index];
        itemArray[index] = newValue;
        return oldValue;
    }

    @Override
    public void clear() {
        sizeArray = 0;
        capacityArray = DEFAULT_CAPACITY;
        itemArray = new Object[capacityArray];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) throws IndexOutOfBoundsException {
        if (index >= size())
            throw new IndexOutOfBoundsException();

        return (T) itemArray[index];
    }

    @Override
    public void add(int index, T item) {
        if (capacityArray == sizeArray)
            ensureCapacity(capacityArray * 3 / 2 + 1);

        //right shift the data
        for (int i = sizeArray; i > index; i--) {
            itemArray[i] = itemArray[i - 1];
        }

        sizeArray++;
        itemArray[index] = item;
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < sizeArray; i++) {
            if (itemArray[i].equals(element))
                return i;
        }
        return -1;
    }

    @Override
    public String toString(){
        String repr = "[";
        MyIterator<T> it = this.iterator();
        while (it.hasNext()){
            repr += it.next();
            if(it.hasNext())
                repr += ",";
        }
        repr += "]";
        return repr;
    }
}
