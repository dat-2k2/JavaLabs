package ru.spbstu.telematics.java.lab2.map;

import ru.spbstu.telematics.java.lab2.list.arraylist.MyArrayList;
import ru.spbstu.telematics.java.lab2.list.linkedlist.MyLinkedList;

import java.util.function.BiConsumer;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private final float MAX_LOAD_FACTOR = 0.75F;
    private final int DEFAULT_CAPACITY = 16;
    private transient MyTable<K, V> hashTable;

    private int size;

    public MyHashMap() {
        hashTable = new MyTable<>(DEFAULT_CAPACITY);
        size = 0;
    }


    @Override
    public void clear() {
        hashTable = new MyTable<>(hashTable.size());
        size = 0;
    }

    void reHashing() {
        int tableSize = hashTable.size();
        int newCap = tableSize * 2;
        MyTable<K, V> newTable = new MyTable<>(newCap);

        //rehash old items
        hashTable.forEach((bucket) -> bucket.forEach((rehashItem) -> {
            int newIndex = rehashItem.hashCode() & (tableSize * 2 - 1);
            newTable.get(newIndex).addHead(rehashItem);
        }));
        hashTable = newTable;
    }

    MyBucketType<K, V> getBucket(Object key) {
        return hashTable.get(indexOf(key));
    }

    @Override
    public V put(K key, V value) throws NullPointerException{
        MyBucketType<K, V> bucket = getBucket(key);
        MyEntry<K, V> possibleEntry = bucket.get(key);
        V oldValue = null;

        if (possibleEntry == null) { // add new entry if the key is not presented
            bucket.addHead(new MyEntry<>(key, value));
            size++;
            //rehash if needed
            if (size > MAX_LOAD_FACTOR * hashTable.size())
                reHashing();
        } else {// change value of existed entry
            oldValue = possibleEntry.value;
            possibleEntry.value = value;
        }
        return oldValue;
    }

    @Override
    public V replace(K key, V value) {
        if (containsKey(key))
            return put(key, value);
        else
            return null;
    }

    @Override
    public boolean remove(Object key) {
        boolean success = getBucket(key).remove(key);
        if (success) size--;
        return success;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(Object item) {
        return hashTable.get(indexOf(item)).contains(item);
    }

    private int indexOf(Object element) {
        return (element == null) ? 0 : (element.hashCode() & (hashTable.size() - 1));
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        hashTable.forEach((bucket) -> bucket.forEach((entry) -> action.accept(entry.key, entry.value)));
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V get(Object key) {
        if (containsKey(key))
            return hashTable.get(indexOf(key)).get(key).value;
        else return null;
    }

    @Override
    public MyArrayList<MyEntry<K, V>> entryArray() {
        MyArrayList<MyEntry<K, V>> entries = new MyArrayList<>(size);
        hashTable.forEach((bucket) -> bucket.forEach(entries::add));
        return entries;
    }

    //class for buckets in table
    static class MyBucketType<K, V> extends MyLinkedList<MyEntry<K, V>> {
        MyBucketType() {
            super();
        }

        MyEntry<K, V> get(Object key) {
            int index = this.indexOf(key);
            if (index > -1)
                return this.get(index);
            else
                return null;
        }
    }


    //the table should be initialized with empty buckets and not null type
    static class MyTable<K, V> extends MyArrayList<MyBucketType<K, V>> {
        MyTable(int cap) {
            super(cap);
            for (int i = 0; i < cap; i++)
                this.add(new MyBucketType<>());
        }
    }

    /**
     * Object for the entry, which hold a key and a value
     *
     * @param <K> the type of keys maintained by this map.
     * @param <V> the type of mapped values
     */
    static public class MyEntry<K, V> {
        K key;
        V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public boolean equals(Object o) {
            if (key.getClass() == o.getClass())
                return key.equals(o);
            else if (this.getClass() == o.getClass())
                return key.equals(((MyEntry<?, ?>) o).key);
            else return false;
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public String toString() {
            return (key.toString() + ": " + value.toString());
        }


    }
}
