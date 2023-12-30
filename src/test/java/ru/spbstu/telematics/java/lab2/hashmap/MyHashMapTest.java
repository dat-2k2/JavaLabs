package ru.spbstu.telematics.java.lab2.hashmap;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;

public class MyHashMapTest {
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

    static class Avalue{
        String name = "Avalue";
    }

    static class Bvalue extends Avalue{

        String name = "Bvalue";
    }

    static class Cvalue extends Avalue{
        String name = "Cvalue";
    }

    final A[] testKey = {new A(1), new B(2), new C(3), new A(4), new C(5)};
    final Avalue[] testValue = {new Bvalue(), new Avalue(), new Cvalue(), new Cvalue(), new Cvalue()};
    MyHashMap<A,Avalue> testHashMap = new MyHashMap<>();
    HashMap<A,Avalue> validHashMap = new HashMap<>();

    @Test
    public void testPut() {
        for (int i = 0; i < testKey.length; i++){
            testHashMap.put(testKey[i], testValue[i]);
            validHashMap.put(testKey[i], testValue[i]);
        }

        validHashMap.forEach((key, val)->{

        });
    }

    @Test
    public void testReplace() {
    }

    @Test
    public void testRemove() {

    }

    public void testSize() {
    }

    public void testContainsKey() {
    }

    public void testIndexOf() {
    }
}