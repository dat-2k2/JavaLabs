package ru.spbstu.telematics.java;

import org.junit.Test;
import ru.spbstu.telematics.java.lab2.LinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class LinkedListTest {
    static final Integer[] arrayTest = {0,1,2,3,4,5};

    //test basic functions
    @Test
    public void testLLEmptyCreate(){
        LinkedList<Integer> listTest = new LinkedList<>();
        assert(listTest.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testLLConstructFromCollection() throws NullPointerException, IndexOutOfBoundsException{
        LinkedList<Integer> listTest = new LinkedList<>(Arrays.asList(arrayTest));
        for (int i = 0; i < arrayTest.length; i++){
            assert (Objects.equals(listTest.get(i), arrayTest[i]));
        }
        assert(listTest.size() == arrayTest.length);
        ArrayList<Integer> tmp = null;
        listTest = new LinkedList<>(tmp);
    }

    @Test
    public void testLLConstructFromArray(){
        LinkedList<Integer> listTest = new LinkedList<>(arrayTest);
        for (int i = 0; i < arrayTest.length; i++){
            assert (Objects.equals(listTest.get(i), arrayTest[i]));
        }
        assert(listTest.size() == arrayTest.length);
    }
    @Test
    public void testAdd(){
        LinkedList<Integer> listTest = new LinkedList<>();
        listTest.add(1);
        listTest.add(2);
        assert(listTest.get(0) == 1);
        assert(listTest.get(1) == 2);
        assert(listTest.size() == 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemove() throws IndexOutOfBoundsException{
        LinkedList<Integer> listTest = new LinkedList<>(arrayTest);
        listTest.remove(1);
        assert(!listTest.contains(1));
        assert(listTest.size() == arrayTest.length-1);
        //test the exception
        listTest.remove(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet() throws IndexOutOfBoundsException{
        LinkedList<Integer> listTest = new LinkedList<>();
        for (int i = 0; i < 3; i++){
            listTest.add((Integer) i);
        }
        assert(listTest.get(2) == 2);

        //test the exception
        listTest.get(5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSet() throws IndexOutOfBoundsException{
        LinkedList<Integer> listTest = new LinkedList<>(arrayTest);
        assert (listTest.set(0,100) == 0);
        assert (listTest.get(0) == 100);
        listTest.set(100,100);
    }

    @Test
    public void testClear(){
        LinkedList<Integer> listTest = new LinkedList<>(arrayTest);
        listTest.clear();
        assert (listTest.isEmpty());
    }
}
