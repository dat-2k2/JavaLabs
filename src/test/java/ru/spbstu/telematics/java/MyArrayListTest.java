package ru.spbstu.telematics.java;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;
import ru.spbstu.telematics.java.lab2.MyIterator;
import ru.spbstu.telematics.java.lab2.arraylist.MyArrayList;
import ru.spbstu.telematics.java.lab2.linkedlist.MyLinkedList;

public class MyArrayListTest {
    @Test(expected = NullPointerException.class)
    public void testConstructor(){
        //test empty constructor
        MyArrayList<Integer> testArray = new MyArrayList<>();
        MyLinkedList<Integer> checkArray = new MyLinkedList<>();

        assert(testArray.size() == checkArray.size());
        assert(testArray.isEmpty() == checkArray.isEmpty());

        //test constructor from collection
        for (int i = 0; i < 5; i ++){
            checkArray.add(i);
        }

        testArray = new MyArrayList<>(checkArray);
        assert(testArray.size() == checkArray.size());

        MyIterator<Integer> itCheckArray = checkArray.iterator();
        testArray.forEach(item -> {
            assert (item.equals(itCheckArray.next()));
        });

        Integer[] checkFlatArray = new Integer[]{0,1,2,3,4,5};
        testArray = new MyArrayList<>(checkFlatArray);
        assert(testArray.size() == checkFlatArray.length);
        for(int i = 0; i < testArray.size(); i++) {
            assert(testArray.get(i) == checkFlatArray[i]);
        }

        checkArray = null;
        testArray = new MyArrayList<>(checkArray);
    }


    @Test(expected = NoSuchElementException.class)
    public void testIterator(){
        MyArrayList<Integer> testArray = new MyArrayList<>();
        ArrayList<Integer> checkArray = new ArrayList<>();

        for (int i = 0 ; i<5 ; i++){
            testArray.add(i);
            checkArray.add(i);
        }

        MyIterator<Integer> itTestArray = testArray.iterator();
        Iterator<Integer> itCheckArray = checkArray.iterator();

        for (int i = 0; i < 5; i++){
            assert (itTestArray.hasNext() == itCheckArray.hasNext());
            assert (itTestArray.next().equals(itCheckArray.next()));
        }

        itTestArray.next();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAdd(){
        MyArrayList<Integer> testArray = new MyArrayList<>();
        ArrayList<Integer> checkArray = new ArrayList<>();

        for (int i = 0 ; i<20 ; i++){
            testArray.add(i);
            checkArray.add(i);
        }

        assert (testArray.size() == 20);
        for(int  i =0 ; i < 20; i++){
            assert(testArray.get(i) == checkArray.get(i));
        }
        checkArray.add(10,100);
        testArray.add(10,100);

        assert (testArray.get(10).equals(checkArray.get(10)));
        testArray.add(100,0);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemove(){
        MyArrayList<Integer> testArray = new MyArrayList<>(new Integer[]{0,1,2,3,4});
        testArray.remove(1);
        assert(!testArray.contains(1));
        testArray.remove(testArray.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSet(){
        MyArrayList<Integer> testArray = new MyArrayList<>(new Integer[]{0,1,2,3});
        Integer oldVal = testArray.set(1,5);
        assert (oldVal == 1);
        assert (testArray.get(1) == 5);

        testArray.set(100,0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet(){
        MyArrayList<Integer> testArray = new MyArrayList<>(new Integer[]{0,1,2,3});
        assert(testArray.get(1) == 1);
        testArray = new MyArrayList<>();
        testArray.get(0);
    }
}
