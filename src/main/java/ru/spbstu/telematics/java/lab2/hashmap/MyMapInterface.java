package ru.spbstu.telematics.java.lab2.hashmap;

import java.util.function.BiConsumer;

public interface MyMapInterface<K,V>{
    /**
     * Associates the specified value with the specified key in this map. If the map previously contained a mapping for the key, the old value is replaced.
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the specified key, or null if there was no such mapping. A null return can also indicate the previously associated value is null.
     */
    V put(K key, V value);

    /**
     *
     * @param key
     * @param value
     * @return
     */
    V replace(K key, V value);

    boolean remove(Object key);

    int size();

    boolean containsKey(Object item);

    int indexOf(Object element);

    void forEach(BiConsumer<? super K, ? super V> action);

}
