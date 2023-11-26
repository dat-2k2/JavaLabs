package ru.spbstu.telematics.java.lab2.linkedlist;

import ru.spbstu.telematics.java.lab2.MyIterable;
import ru.spbstu.telematics.java.lab2.MyIterator;

import java.util.NoSuchElementException;

/**
 * An implementation of generic Linked List in Java
 * @param <T> generic type
 */
public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    int sizeList;
    Node<T> headNode;
    Node<T> tailNode;

    @Override
    public MyIterator<T> iterator() {
        return new MyIterator<T>() {
            Node<T> current = headNode;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() throws NoSuchElementException {
                if (!hasNext())
                    throw new NoSuchElementException();
                T data = current.dataNode;
                current = current.nextNode;
                return data;

            }
        };
    }


    /**
     * Generic Node class
     * @param <T>
     */
    static class Node<T>{
        T dataNode;
        Node<T> prevNode;
        Node<T> nextNode;
        Node(){

        }
        Node(T element){
            dataNode = element;
        }
    }

    /**
     * Create an empty list
     */
    public MyLinkedList(){
        sizeList = 0;
        headNode = null;
        tailNode = null;
    }

    /**
     * Create a list from the given collection
     * @param collection the collection for the list
     * @throws NullPointerException if the collection is null object
     */

    public MyLinkedList(MyIterable<? extends T> collection) throws NullPointerException{
        if (collection == null)
            throw new NullPointerException();
        collection.forEach(this::add);
    }

    /**
     * Create a list from a flat array
     * @param array the flat array for the list
     */
    public MyLinkedList(T[] array){
        for (T e: array){
            this.add(e);
        }
    }
    public int size() {
        return sizeList;
    }

    public boolean isEmpty() {

        return sizeList == 0;
    }

    public boolean contains(Object element) {
        if (element == null)
            return isEmpty();
        return indexOf(element) > -1;
    }

    @Override
    public int indexOf(Object element) {
        Node<T> tmp = this.headNode;
        int counter = 0;
        while (tmp != null){
            if (tmp.dataNode.equals(element)){
                return counter;
            }
            tmp = tmp.nextNode;
            counter++;
        }
        return -1;
    }

    public void add(T element){
        Node<T> tmp = tailNode;
        tailNode = new Node<>(element);
        tailNode.prevNode = tmp;
        tailNode.nextNode = null;
        if (tmp != null)
            tmp.nextNode = tailNode;
        else
            //add to empty list
            headNode = tailNode;

        //increase the size
        sizeList++;
    }

    /**
     * Add an element to the head of list
     * @param element the element to add
     */
    public void addHead(T element){
        Node<T> tmp = headNode;

        headNode = new Node<>(element);

        headNode.nextNode = tmp;
        headNode.prevNode = tmp.prevNode;
        tmp.prevNode = headNode;

        sizeList++;
    }

    public void remove(int index) throws IndexOutOfBoundsException {
        if (index > sizeList - 1)
            throw new IndexOutOfBoundsException();

        //firstly, find the deleting node
        Node<T> delNode = headNode;
        for (int i = 0; i < index; i++){
            delNode = delNode.nextNode;
        }

        //reassign ref to neighbor Nodes
        //the previous one
        if (delNode.prevNode != null)
            delNode.prevNode.nextNode = delNode.nextNode;
        else{//remove the head
            headNode = headNode.nextNode;
            headNode.prevNode = null;
        }

        //the successive one
        if (delNode.nextNode != null)
            delNode.nextNode.prevNode = delNode.prevNode;
        else {//remove the tail
            tailNode = tailNode.prevNode;
            tailNode.nextNode = null;
        }

        //remember to update the size
        sizeList--;
    }

    /**
     * Get the element at position index
     * @param index of the element
     * @return the element at position index
     * @throws IndexOutOfBoundsException when index is out of range
     */
    public T get(int index) throws IndexOutOfBoundsException{
        if (index > sizeList - 1 || index < 0)
            throw new IndexOutOfBoundsException();

        Node<T> res = headNode;
        for (int i = 0; i < index; i++){
            res = res.nextNode;
        }
        return res.dataNode;
    }

    /**
     * Replaces the element at the specified position in the list with the specified element
     * @param index index of the element to replace
     * @param element the element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException when index is out of range
     */
    public T set(int index, T element) throws IndexOutOfBoundsException{
        if (index > sizeList - 1 || index < 0)
            throw new IndexOutOfBoundsException();

        Node<T> nodeReplace = headNode;
        for (int i = 0; i < index; i++){
            nodeReplace = nodeReplace.nextNode;
        }
        T oldData = nodeReplace.dataNode;
        nodeReplace.dataNode = element;
        return oldData;
    }

    public void clear() {
        //use automatic garbage collection
        headNode = null;
        tailNode = null;
        sizeList = 0;
    }
}
