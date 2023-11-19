package ru.spbstu.telematics.java.lab2.arraylist;

import ru.spbstu.telematics.java.lab2.MyIterable;
import ru.spbstu.telematics.java.lab2.MyIterator;

import java.util.NoSuchElementException;

// a wrapping class for plain array
public class MyArrayList<T> implements MyArrayListInterface<T> {
    private static final int DEFAULT_CAPACITY = 10;
    int sizeArray;
    int capacityArray;
    T[] itemArray;

    public MyArrayList(){
        sizeArray = 0;
        capacityArray = 10;
        itemArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int cap) throws IllegalArgumentException{
        if (cap < 0)
            throw new IllegalArgumentException();
        sizeArray = 0;
        capacityArray = cap;
        itemArray = (T[]) new Object[cap];
    }

    public MyArrayList(MyIterable<? extends T> collection){
        sizeArray = collection.size();
        capacityArray = collection.size();
        itemArray =  (T[]) new Object[capacityArray];
        MyIterator<? extends T> it = collection.iterator();
        for (int i = 0; i < sizeArray; i++){
            itemArray[i] = it.next();
        }
    }

    public MyArrayList(T[] array){
        sizeArray = array.length;
        capacityArray = array.length;
        itemArray = (T[]) new Object[capacityArray];
        System.arraycopy(array,0,itemArray,0,array.length);
    }

    @Override
    public MyIterator<T> iterator() {
        return new MyIterator<T>() {
            //no need to include the data bc it's safe in the array.
            int index;
            @Override
            public boolean hasNext() {
                return index < sizeArray;
            }

            @Override
            public T next() throws NoSuchElementException {
                if (hasNext()){
                    return itemArray[index++];
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override
    public int size() {
        return sizeArray;
    }

    @Override
    public boolean contains(Object item) {
        return indexOf(item) > -1;
    }

    @Override
    public void add(T item) {
        add(sizeArray, item);
    }

    @Override
    public boolean isEmpty() {
        return sizeArray == 0;
    }

    @Override
    public void ensureCapacity(int newCapacity) {
        if (newCapacity < capacityArray)
            return;

        T[] oldArray = itemArray;
        itemArray = (T[]) new Object[newCapacity];

        capacityArray = newCapacity;
        // copy the old data to the new array;
        System.arraycopy(oldArray, 0, itemArray, 0, sizeArray);
    }

    @Override
    public void trimToSize() {
        ensureCapacity(sizeArray +1);
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException{
        if (index >= sizeArray)
            throw new IndexOutOfBoundsException();

        T removeItem = itemArray[index];
        //left-shift the data after deleted element
        for (int i = index; i < sizeArray - 1; i++){
            itemArray[i] = itemArray[i+1];
        }
        sizeArray--;
        return removeItem;
    }

    @Override
    public T set(int index, T newValue) throws IndexOutOfBoundsException{
        if (index >= size())
            throw new IndexOutOfBoundsException();

        T oldValue = itemArray[index];
        itemArray[index] = newValue;

        return oldValue;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException{
        if (index >= size())
            throw new IndexOutOfBoundsException();

        return itemArray[index];
    }

    @Override
    public void add(int index, T item) {
        if (capacityArray == sizeArray)
            ensureCapacity(capacityArray*3/2+1);

        //right shift the data
        for (int i = sizeArray; i > index; i--){
            itemArray[i] = itemArray[i-1];
        }

        sizeArray++;
        itemArray[index] = item;
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < sizeArray; i++){
            if (itemArray[i].equals(element))
                return i;
        }
        return -1;
    }
}
