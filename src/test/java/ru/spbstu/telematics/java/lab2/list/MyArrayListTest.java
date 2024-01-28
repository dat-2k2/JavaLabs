package ru.spbstu.telematics.java.lab2.list;

import org.junit.Test;
import ru.spbstu.telematics.java.lab2.MyIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Test class MyArrayList
 */
public class MyArrayListTest extends MyListTest{
    protected final A[] testData = {new A(1), new B(2), new C(3), new A(4), new C(5)};
    public MyArrayListTest(){
        super.testArray = new MyArrayList<>(Arrays.asList(testData));
        super.validArray = new ArrayList<>(Arrays.asList(testData));
    }
}
