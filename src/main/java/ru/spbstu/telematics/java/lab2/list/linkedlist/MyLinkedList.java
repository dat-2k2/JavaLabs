package ru.spbstu.telematics.java.lab2.list.linkedlist;

import ru.spbstu.telematics.java.lab2.MyIterable;
import ru.spbstu.telematics.java.lab2.MyIterator;
import ru.spbstu.telematics.java.lab2.list.MyList;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * An implementation of generic Linked List in Java
 *
 * @param <T> generic type
 */
public class MyLinkedList<T> implements MyList<T> {
    int sizeList;
    transient Node headNode;
    transient Node tailNode;

    /**
     * Create an empty list
     */
    public MyLinkedList() {
        sizeList = 0;
        headNode = null;
        tailNode = null;
    }
//----------------------------------------------------------

    /**
     * Create a list from the given collection
     *
     * @param collection the collection for the list
     * @throws NullPointerException if the collection is null object
     */

    public MyLinkedList(MyIterable<? extends T> collection) throws NullPointerException {
        if (collection == null)
            throw new NullPointerException();
        collection.forEach(this::add);
    }

    public MyLinkedList(Collection<? extends T> collection) throws NullPointerException {
        if (collection == null)
            throw new NullPointerException();
        collection.forEach(this::add);
    }

    @Override
    public MyIterator<T> iterator() {
        return new MyIterator<T>() {
            Node current = headNode;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() throws NoSuchElementException {
                if (!hasNext())
                    throw new NoSuchElementException();
                Object data = current.value;
                current = current.nextNode;
                return (T) data;
            }
        };
    }


//------------------------------------------------------------------------

    public int size() {
        return sizeList;
    }

    public boolean isEmpty() {
        return sizeList == 0;
    }

    public boolean contains(Object element) {
        if (element == null)
            return isEmpty();
        return (indexOf(element) > -1);
    }

    @Override
    public int indexOf(Object element) {
        Node tmp = this.headNode;
        int counter = 0;
        while (tmp != null) {
            if (tmp.value.equals(element)) {
                return counter;
            }
            tmp = tmp.nextNode;
            counter++;
        }
        return -1;
    }

    @Override
    public void add(T element) throws NullPointerException {
        if (element == null)
            throw new NullPointerException();

        Node tmp = tailNode;
        tailNode = new Node(element);
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
     *
     * @param element the element to add
     */
    public void addHead(T element) {
        Node tmp = headNode;

        headNode = new Node(element);

        headNode.nextNode = tmp;
        headNode.prevNode = null;
        if (tmp != null)
            tmp.prevNode = headNode;

        sizeList++;
    }

    public void add(int index, T element) throws IndexOutOfBoundsException {
        if (index >= sizeList)
            throw new IndexOutOfBoundsException();
        tailNode.nextNode = new Node();
        tailNode.nextNode.prevNode = tailNode;
        tailNode = tailNode.nextNode;

        Node tmp = tailNode;
        sizeList++;

        for (int i = index + 1; i < sizeList; i++) {
            tmp.value = tmp.prevNode.value;
            tmp = tmp.prevNode;
        }
        tmp.value = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index > sizeList - 1 || index < 0)
            throw new IndexOutOfBoundsException();

        //firstly, find the deleting node
        Node delNode = headNode;
        for (int i = 0; i < index; i++) {
            delNode = delNode.nextNode;
        }

        Object returnVal = delNode.value;
        //size = 1;
        if (sizeList == 1){
            this.clear();
            return (T) returnVal;
        }
        //reassign ref to neighbor Nodes
        //the previous one
        if (delNode.prevNode != null)
            delNode.prevNode.nextNode = delNode.nextNode;
        else {//remove the head
            headNode = headNode.nextNode;
            if (headNode != null)
                headNode.prevNode = null;
        }

        //the successive one
        if (delNode.nextNode != null)
            delNode.nextNode.prevNode = delNode.prevNode;
        else {//remove the tail
            tailNode = tailNode.prevNode;
            if (tailNode != null)
                tailNode.nextNode = null;
        }

        //remember to update the size
        sizeList--;
        return (T) returnVal;
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
    public T get(int index) throws IndexOutOfBoundsException {
        if (index > sizeList - 1 || index < 0)
            throw new IndexOutOfBoundsException();

        Node res = headNode;
        for (int i = 0; i < index; i++) {
            res = res.nextNode;
        }
        return (T) res.value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T element) throws IndexOutOfBoundsException {
        if (index > sizeList - 1 || index < 0)
            throw new IndexOutOfBoundsException();

        Node nodeReplace = headNode;
        for (int i = 0; i < index; i++) {
            nodeReplace = nodeReplace.nextNode;
        }
        Object oldData = nodeReplace.value;
        nodeReplace.value = element;
        return (T) oldData;
    }

    public void clear() {
        //use automatic garbage collection
        headNode = null;
        tailNode = null;
        sizeList = 0;
    }

    /**
     * Node class
     */
    static class Node {
        transient Object value;
        Node prevNode;
        Node nextNode;

        Node() {

        }

        Node(Object element) {
            value = element;
        }
        @Override
        public String toString(){
            if (value != null)
                return value.toString();
            else
                return "null";
        }
    }

    @Override
    public String toString(){
        String res = "";
        Node tmp = headNode;
        while (tmp != null){
            res += (tmp.toString() + ", ");
            tmp=tmp.nextNode;
        }
        return res;
    }
}
