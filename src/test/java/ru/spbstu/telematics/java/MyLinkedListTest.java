package ru.spbstu.telematics.java;

import org.junit.Test;
import ru.spbstu.telematics.java.lab2.linkedlist.MyLinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MyLinkedListTest {
    static final Integer[] arrayTest = {0,1,2,3,4,5};

    //test basic functions

    /**
     * Test creating an empty Linked List
     */
    @Test
    public void testLLEmptyCreate(){
        MyLinkedList<Integer> listTest = new MyLinkedList<>();
        assert(listTest.isEmpty());
    }

    /**
     * Test creating Linked List from a Collection
     * @throws NullPointerException if the collection is null
     */
    @Test(expected = NullPointerException.class)
    public void testLLConstructFromCollection() throws NullPointerException, IndexOutOfBoundsException{
        MyLinkedList<Integer> listTest = new MyLinkedList<>(Arrays.asList(arrayTest));
        for (int i = 0; i < arrayTest.length; i++){
            assert (Objects.equals(listTest.get(i), arrayTest[i]));
        }
        assert(listTest.size() == arrayTest.length);
        ArrayList<Integer> tmp = null;
        listTest = new MyLinkedList<>(tmp);
    }

    /**
     * Test creating Linked List from a flat array
     */
    @Test
    public void testLLConstructFromArray(){
        MyLinkedList<Integer> listTest = new MyLinkedList<>(arrayTest);
        for (int i = 0; i < arrayTest.length; i++){
            assert (Objects.equals(listTest.get(i), arrayTest[i]));
        }
        assert(listTest.size() == arrayTest.length);
    }

    /**
     * Test add member to the last
     */
    @Test
    public void testAdd(){
        MyLinkedList<Integer> listTest = new MyLinkedList<>();
        listTest.add(1);
        listTest.add(2);
        assert(listTest.get(0) == 1);
        assert(listTest.get(1) == 2);
        assert(listTest.size() == 2);
    }

    /**
     * Test addHead()
     */
    @Test
    public void testAddHead(){
        MyLinkedList<Integer> listTest = new MyLinkedList<>(arrayTest);
        listTest.addHead(-1);
        assert (listTest.get(0) == -1);
    }



    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemove() throws IndexOutOfBoundsException{
        MyLinkedList<Integer> listTest = new MyLinkedList<>(arrayTest);
        listTest.remove(1);
        assert(!listTest.contains(1));
        assert(listTest.size() == arrayTest.length-1);
        //test the exception
        listTest.remove(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet() throws IndexOutOfBoundsException{
        MyLinkedList<Integer> listTest = new MyLinkedList<>();
        for (int i = 0; i < 3; i++){
            listTest.add((Integer) i);
        }
        assert(listTest.get(2) == 2);

        //test the exception
        listTest.get(5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSet() throws IndexOutOfBoundsException{
        MyLinkedList<Integer> listTest = new MyLinkedList<>(arrayTest);
        assert (listTest.set(0,100) == 0);
        assert (listTest.get(0) == 100);
        listTest.set(100,100);
    }

    @Test
    public void testClear(){
        MyLinkedList<Integer> listTest = new MyLinkedList<>(arrayTest);
        listTest.clear();
        assert (listTest.isEmpty());
    }
}
