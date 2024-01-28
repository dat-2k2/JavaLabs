package ru.spbstu.telematics.java.lab2.map;

import ru.spbstu.telematics.java.lab2.list.MyArrayList;

import java.util.function.BiConsumer;

/**
 * An object that maps keys to values. A map cannot contain duplicate keys; each key can map to at most one value.
 *
 * @param <K> the type of keys maintained by this map.
 * @param <V> the type of mapped values
 */
public interface MyMap<K, V> {

    /**
     * Removes all of the elements from this map
     */
    void clear();

    /**
     * Associates the specified value with the specified key in this map. If the map previously contained a mapping for the key, the old value is replaced.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the specified key, or null if there was no such mapping. A null return can also indicate the previously associated value is null.
     */
    V put(K key, V value);

    /**
     * Replaces the entry for the specified key only if currently mapped to the specified value.
     *
     * @param key   key with which the specified value is associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the specified key, or null if there was no such mapping. A null return can also indicate the previously associated value is null.
     */
    V replace(K key, V value);

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key key to be removed from the map
     * @return the previous value associated with the specified key, or null if there was no such mapping. A null return can also indicate the previously associated value is null.
     */
    boolean remove(Object key);

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of map entries
     */
    int size();

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param item the key to check
     * @return true if this map contains a mapping for the specified key.
     */
    boolean containsKey(Object item);

    /**
     * Performs the given action for each entry in this map until all entries have been processed or the action throws an exception.
     *
     * @param action the function to apply to each entry
     */
    void forEach(BiConsumer<? super K, ? super V> action);

    /**
     * Check there is not any mapping in the map
     *
     * @return true if it is empty
     */
    boolean isEmpty();

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key with which associated the finding value
     * @return the value associated with the key
     */
    V get(Object key);

    /**
     * Return a set for all entries in the map. Being too lazy to implement the Set, but theoretically the entries are unordered, which makes index meaningless.
     *
     * @return an array containing all entries in the map
     */
    MyArrayList<MyHashMap.MyEntry<K, V>> entryArray();
}
