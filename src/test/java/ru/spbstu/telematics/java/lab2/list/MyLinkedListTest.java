package ru.spbstu.telematics.java.lab2.list;

import org.junit.Test;
import ru.spbstu.telematics.java.lab2.MyIterator;

import java.util.*;

/**
 * A test class for MyLinkedList. Validate with another LinkedList object.
 */
public class MyLinkedListTest extends MyListTest{
    protected final A[] testData = {new A(1), new B(2), new C(3), new A(4), new C(5)};
    public MyLinkedListTest(){
        super.testArray = new MyLinkedList<>(Arrays.asList(testData));
        super.validArray = new LinkedList<>(Arrays.asList(testData));
    }
}
