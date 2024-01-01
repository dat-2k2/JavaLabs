package ru.spbstu.telematics.java.lab2.hashmap;

import ru.spbstu.telematics.java.lab2.list.arraylist.MyArrayList;
import ru.spbstu.telematics.java.lab2.list.linkedlist.MyLinkedList;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class MyHashMap<K, V> implements MyMapInterface<K, V> {
    final float MAX_LOAD_FACTOR = 0.75F;
    final int DEFAULT_CAPACITY = 16;
    transient MyTable<K, V> hashTable;

    int size;
    int usedSlots;
    float loadFactor;

    public MyHashMap() {
        loadFactor = 0;
        hashTable = new MyTable<>(DEFAULT_CAPACITY);
        size = 0;
        usedSlots = 0;
    }


    @Override
    public void clear() {
        loadFactor = 0;
        hashTable = new MyTable<>(hashTable.size());
        size = 0;
        usedSlots = 0;
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

        //load factor update
        AtomicInteger used_slots = new AtomicInteger();
        hashTable.forEach((bucket) -> {
            if (!bucket.isEmpty())
                used_slots.getAndIncrement();
        });
        usedSlots = used_slots.get();
        loadFactor = (float) usedSlots / hashTable.size();
    }

    MyBucketType<K, V> getBucket(Object key) {
        return hashTable.get(indexOf(key));
    }

    @Override
    public V put(K key, V value) {
        MyBucketType<K, V> bucket = getBucket(key);
        MyEntry<K, V> possibleEntry = bucket.get(key);
        V oldValue = null;
        if (possibleEntry == null) { // add new entry
            size++;
            //rehash if needed
            if (loadFactor >= MAX_LOAD_FACTOR)
                reHashing();

            bucket.addHead(new MyEntry<>(key, value));
            if (bucket.size() == 1)
                loadFactor = (float) (++usedSlots) / hashTable.size();
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
        if (containsKey(key)) {
            size--;
            MyBucketType<K, V> bucket = getBucket(key);
            if (bucket.size() == 1)
                loadFactor = (float) (--usedSlots) / hashTable.size();
            bucket.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(Object item) {
        return hashTable.get(indexOf(item)).contains(item);
    }

    int indexOf(Object element) {
        return (element == null) ? 0 : (element.hashCode() & (hashTable.size() - 1));
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        hashTable.forEach((bucket) -> bucket.forEach((entry) -> action.accept(entry.key, entry.value)));
    }

    @Override
    public boolean isEmpty() {
        return usedSlots == 0;
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
