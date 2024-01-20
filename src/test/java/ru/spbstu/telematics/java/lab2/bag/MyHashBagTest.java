package ru.spbstu.telematics.java.lab2.bag;


import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;
import org.junit.Test;
import ru.spbstu.telematics.java.lab2.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * There is no built-in Bag structure in Java lib, so just check the theory properties
 */
public class MyHashBagTest {
    B item1 = new B(1);
    C item2 = new C(2);
    A item3 = new A(3);
    // add a new object to check equality rule of class General
    final A[] testData = {item1, item2, item3, item2, item3, new C(2)};
    Bag<A> validBag = new HashBag<>(Arrays.asList(testData));;
    MyBag<General> testBag = new MyHashBag<>(Arrays.asList(testData));;

    /**
     * Assert to validate a MyHashBag
     * @param test MyHashBag object
     * @param valid a HashBag object with the same data to validate
     */
    void equal(MyBag<? extends General> test, Bag<? extends General> valid){
        assert(test.size() == valid.size());
        test.forEach((item)->{
            assert (valid.getCount(item) == test.getCount(item));
        });
    }
    /**
     * Test constructor
     */
    @Test
    public void testConstructor() {
        assert (new MyHashBag<>().isEmpty());
        assert (testBag.size() == validBag.size());
        assert (new B(1).equals(new B(1)));
        equal(testBag,validBag);
    }

    /**
     * Test iterator of MyBag. Expect to iterate through all objects in MyBag
     */
    @Test
    public void testIterator(){
        assert (!new MyHashBag<General>().iterator().hasNext());
        MyIterator<General> it = testBag.iterator();
        int count = 1;
        General currentItem = null;
        while (it.hasNext()){
            General item = it.next();
            if (currentItem == null)
                currentItem = item;

            if (count < testBag.getCount(item)){
                count++;
                assert (currentItem.equals(item));
            }
            else {
                assert (validBag.getCount(currentItem) == count);
                count = 1;
                currentItem = null;
            }
        }

    }
    /**
     * Test getCount() method of MyBag. Expect to return the correct amount of each item in the Bag
     */
    @Test
    public void testGetCount() {
        testBag.forEach((item) -> {
            assert (testBag.getCount(item) == validBag.getCount(item));
        });
    }

    /**
     * Test add() method of MyBag. The method does not receive null input
     */
    @Test(expected = NullPointerException.class)
    public void testAdd() {
        testBag.add(new B(100));
        validBag.add(new B(100));
        equal(testBag,validBag);

        testBag.add(null);
    }

    /**
     * Test remove() method of MyBag
     */
    @Test
    public void testRemove() {
        assert (testBag.remove(testData[2]));
        assert (validBag.remove(testData[2]));
        assert(testBag.size() == validBag.size());

        equal(testBag,validBag);

        assert (!testBag.remove(new A(100)));
    }

    /**
     * Test clear() method of Bag
     */
    @Test
    public void testClear(){
        testBag.clear();
        assert (testBag.isEmpty());
    }
    /**
     * Create custom classes to check variance
     */
    static class General{
        int id;
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            A a = (A) o;
            return id == a.id;
        }

        @Override
        public int hashCode(){
            return Objects.hash(id);
        }

        @Override
        public String toString(){
            return Integer.toString(id);
        }
    }
    static class A extends General{
        A(int id) {
            this.id = id;
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
