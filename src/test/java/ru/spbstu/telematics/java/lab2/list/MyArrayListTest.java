package ru.spbstu.telematics.java.lab2.list;

import org.junit.Test;
import ru.spbstu.telematics.java.lab2.MyIterator;
import ru.spbstu.telematics.java.lab2.list.arraylist.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Test class MyArrayList
 */
public class MyArrayListTest {

    final A[] testData = {new A(1), new B(2), new C(3), new A(4), new C(5)};
    MyArrayList<A> testArray = new MyArrayList<>(Arrays.asList(testData));
    ArrayList<A> validArray = new ArrayList<>(Arrays.asList(testData));

    //---------------------------------------------------------------

    /**
     * Test default constructor for MyArrayList
     */
    @Test(expected = NullPointerException.class)
    public void testConstructor() {
        assert ((new MyArrayList<>()).isEmpty());
        assert (testArray.size() == validArray.size());

        ArrayList<Object> nullObj = null;
        new MyArrayList<Object>(nullObj);
    }

    /**
     * Test iterator function of MyArrayList, comparing to the built-in ArrayList.
     */
    @Test(expected = NoSuchElementException.class)
    public void testIterator() {
        MyIterator<A> itTestArray = testArray.iterator();
        validArray.forEach((item) -> {
            assert (itTestArray.next().equals(item));
        });

        itTestArray.next();
    }

    /**
     * Test method add() of MyArrayList, comparing to the built-in ArrayList
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAdd() {
        A addObject1 = new B(10);
        testArray.add(addObject1);
        validArray.add(addObject1);

        int sizeArr = testArray.size();
        assert (sizeArr == validArray.size());

        assert (testArray.get(sizeArr - 1).equals(validArray.get(sizeArr - 1)));

        // test add to specific index
        int indexAdd = sizeArr / 2;
        testArray.add(indexAdd, addObject1);
        validArray.add(indexAdd, addObject1);

        assert (testArray.get(indexAdd).equals(validArray.get(indexAdd)));

        testArray.add(100, new A(0));
    }

    /**
     * Test remove() method of MyArrayList.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemove() {
        assert (testArray.remove(1).equals(validArray.remove(1)));
        testArray.remove(testArray.size());
    }

    /**
     * Test set() method of MyArrayList
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSet() {
        int setIndex = 3;
        A setObject = new C(15);
        testArray.set(setIndex, setObject);
        validArray.set(setIndex, setObject);
        assert (testArray.get(setIndex).equals(validArray.get(setIndex)));
        testArray.set(100, new A(100));
    }

    /**
     * Test get() method of MyArrayList
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet() {
        assert (testArray.get(1).equals(validArray.get(1)));
        testArray.get(testArray.size());
    }

    /**
     * Test all constructors of MyArrayList: empty, from collection, from flat array and null case.
     */
    static class A {
        int id;

        A(int id) {
            this.id = id;
        }

        public final int getId() {
            return id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            A a = (A) o;
            return id == a.id;
        }
    }

    static class B extends A {
        B(int id) {
            super(id);
        }
    }

    static class C extends A {
        C(int id) {
            super(id);
        }
    }
}
