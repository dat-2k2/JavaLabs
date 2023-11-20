package ru.spbstu.telematics.java;

import org.junit.Test;
import ru.spbstu.telematics.java.lab2.MyIterator;
import ru.spbstu.telematics.java.lab2.arraylist.MyArrayList;
import ru.spbstu.telematics.java.lab2.bag.MyBag;

import java.util.NoSuchElementException;


// there is no Bag structure in java lib, just check the theory properties
public class MyBagTest {
    @Test(expected =  NullPointerException.class)
    public void testConstructor(){
        MyBag<Integer> testBag = new MyBag<>();
        assert (testBag.isEmpty());

        testBag = new MyBag<>(new Integer[]{1,1,2,3,4});

        assert (testBag.size() == 5);
        assert (testBag.getOccur(1) == 2);
        assert (testBag.contains(1));
        assert (testBag.contains(2));
        assert (testBag.contains(3));
        assert (testBag.contains(4));

        MyArrayList<Integer> checkArray  = new MyArrayList<>();
        checkArray.add(1);
        testBag = new MyBag<>(checkArray);
        assert (testBag.contains(1));
        assert (testBag.size() == 1);
        assert (testBag.getOccur(1) == 1);

        checkArray = null;
        testBag = new MyBag<>(checkArray);
    }


    @Test(expected = NoSuchElementException.class)
    public void testIterator(){
        Integer[] tmp = {1,1,2,3,4};
        MyBag<Integer> testBag = new MyBag<>(tmp);

        MyIterator<Integer> it = testBag.iterator();
        while (it.hasNext()){
            it.next();
        }
        it.next();
    }

    @Test
    public void testGetOccur(){
        Integer[] tmp = {1,1,2,3,4};
        MyBag<Integer> testBag = new MyBag<>(tmp);

        assert (testBag.getOccur(1) == 2);
        assert (testBag.getOccur(10) == 0);
        assert (testBag.getOccur(2) == 1);
    }

    @Test(expected = NullPointerException.class)
    public void testAdd(){
        Integer[] tmp = {1,1,2,3,4};
        MyBag<Integer> testBag = new MyBag<>(tmp);

        testBag.add(1);
        assert (testBag.getOccur(1) == 3);
        assert (testBag.size() == 6);
        testBag.add(10);
        assert (testBag.getOccur(10) == 1);
        assert (testBag.size() == 7);
        testBag.add(null);
    }

    @Test
    public void testRemove(){
        Integer[] tmp = {1,1,2,3,4};
        MyBag<Integer> testBag = new MyBag<>(tmp);
        testBag.remove(1);
        assert (testBag.getOccur(1) == 1);
        testBag.remove(1);
        assert (testBag.getOccur(1) == 0);
        assert (testBag.size() == 3);
        assert (!testBag.remove(10));
    }


}
