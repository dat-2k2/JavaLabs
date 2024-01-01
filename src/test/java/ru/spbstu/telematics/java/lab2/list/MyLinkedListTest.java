package ru.spbstu.telematics.java.lab2.list;

import org.junit.Test;
import ru.spbstu.telematics.java.lab2.MyIterator;
import ru.spbstu.telematics.java.lab2.list.linkedlist.MyLinkedList;

import java.util.*;

public class MyLinkedListTest {
    final A[] testData = {new A(1), new B(2), new C(3), new A(4), new C(5)};
    MyLinkedList<A> testArray = new MyLinkedList<>(Arrays.asList(testData));
    LinkedList<A> validArray = new LinkedList<>(Arrays.asList(testData));

    /**
     * Test constructor Linked List
     *
     * @throws NullPointerException if the collection is null
     */
    @Test(expected = NullPointerException.class)
    public void testLLConstructFromCollection() throws NullPointerException, IndexOutOfBoundsException {
        assert (new MyLinkedList<A>().isEmpty());
        assert (testArray.size() == validArray.size());
        Iterator<A> validIt = validArray.iterator();
        for (A item: testData){
            assert (testArray.contains(item));
        }

        ArrayList<A> nullObj = null;
        new MyLinkedList<A>(nullObj);
    }

    @Test(expected = NoSuchElementException.class)
    public void testIterator() {
        MyIterator<A> testIt = testArray.iterator();
        Iterator<A> validIt = validArray.iterator();
        while (testIt.hasNext()) {
            assert (testIt.next() == validIt.next());
        }

        testIt.next();
    }

    /**
     * Test add member to the last
     */
    @Test
    public void testAdd() {
        A addObj = new C(10);
        testArray.add(addObj);
        validArray.add(addObj);
    }

    /**
     * Test addHead()
     */
    @Test
    public void testAddHead() {
        A addObj = new C(1000);
        validArray.addFirst(addObj);
        testArray.addHead(addObj);
        assert (testArray.get(0).equals(validArray.get(0)));
    }

    @Test
    public void testAddPosition() {
        A addObj = new B(100);
        validArray.add(3, addObj);
        testArray.add(3, addObj);

        for (int i = 0; i < validArray.size(); i++)
            assert (testArray.get(i).equals(validArray.get(i)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemove() throws IndexOutOfBoundsException {
        A removeObj = new C(1000);
        validArray.addFirst(removeObj);
        testArray.addHead(removeObj);
        assert (testArray.remove(0).equals(validArray.remove(0)));
        assert (testArray.remove(1).equals(validArray.remove(1)));
        //test the exception
        testArray.remove(testArray.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet() throws IndexOutOfBoundsException {
        for (int i = 0; i < validArray.size(); i++) {
            assert (testArray.get(i).equals(validArray.get(i)));
        }

        testArray.get(testArray.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSet() throws IndexOutOfBoundsException {
        C setObj = new C(100);
        testArray.set(2, setObj);
        validArray.set(2, setObj);
        assert (testArray.get(2) == validArray.get(2));
        assert (testArray.get(2) == setObj);

        testArray.set(testArray.size(), new A(1));
    }

    @Test
    public void testClear() {
        testArray.clear();
        validArray.clear();
        assert (testArray.size() == validArray.size());
    }

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
