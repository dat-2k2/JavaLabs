package ru.spbstu.telematics.java.lab2;

import sun.awt.image.ImageWatched;

import javax.lang.model.element.Element;
import java.util.Collection;
import java.util.Iterator;

public class LinkedList<E> implements ListCustom<E>{
    int sizeList;
    Node<E> headNode;
    Node<E> tailNode;


    /**
     * Node type
     * @param <E>
     */
    static class Node<E>{
        E dataNode;
        Node<E> prevNode;
        Node<E> nextNode;
        Node(){

        }
        Node(E element){
            dataNode = element;
        }
    }

    public LinkedList(){
        sizeList = 0;
        headNode = null;
        tailNode = null;
    }

    public LinkedList(Collection<? extends E> collection) throws NullPointerException{
        if (collection == null)
            throw new NullPointerException();
        for (E e: collection){
            this.add(e);
        }
    }

    public LinkedList(E[] array){
        for (E e: array){
            this.add(e);
        }
    }
    @Override
    public int size() {
        return sizeList;
    }

    @Override
    public boolean isEmpty() {
        return sizeList == 0;
    }

    @Override
    public boolean contains(E element) {
        if (element == null)
            return isEmpty();
        return indexOf(element) > -1;
    }

    @Override
    public int indexOf(E element) {
        Node<E> tmp = this.headNode;
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

    @Override
    public void add(E element){
        Node<E> tmp = tailNode;
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

    public void addHead(E element){
        Node<E> tmp = headNode;

        headNode = new Node<>(element);

        headNode.nextNode = tmp;
        headNode.prevNode = tmp.prevNode;
        tmp.prevNode = headNode;

        sizeList++;
    }

    @Override
    public void remove(int index) throws IndexOutOfBoundsException {
        if (index > sizeList - 1)
            throw new IndexOutOfBoundsException();

        //firstly, find the deleting node
        Node<E> delNode = headNode;
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

    @Override
    public E get(int index) throws IndexOutOfBoundsException{
        if (index > sizeList - 1 || index < 0)
            throw new IndexOutOfBoundsException();

        Node<E> res = headNode;
        for (int i = 0; i < index; i++){
            res = res.nextNode;
        }
        return res.dataNode;
    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException{
        if (index > sizeList - 1 || index < 0)
            throw new IndexOutOfBoundsException();

        Node<E> nodeReplace = headNode;
        for (int i = 0; i < index; i++){
            nodeReplace = nodeReplace.nextNode;
        }
        E oldData = nodeReplace.dataNode;
        nodeReplace.dataNode = element;
        return oldData;
    }

    @Override
    public void clear() {
        //use automatic garbage collection
        headNode = null;
        tailNode = null;
        sizeList = 0;
    }
}
