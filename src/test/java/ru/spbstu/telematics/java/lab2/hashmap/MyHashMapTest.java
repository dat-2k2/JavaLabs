package ru.spbstu.telematics.java.lab2.hashmap;

import org.junit.Test;

import java.util.HashMap;
import java.util.Objects;

public class MyHashMapTest {
    final A[] testKey = {new A(1), new B(2), new C(3), new A(4), new C(5)};
    final Avalue[] testValue = {new Bvalue(), new Avalue(), new Cvalue(), new Cvalue(), new Cvalue()};
    MyHashMap<A, Avalue> testHashMap = new MyHashMap<>();
    HashMap<A, Avalue> validHashMap = new HashMap<>();

    public MyHashMapTest() {
        testPut();
    }

    @Test
    public void testPut() {
        for (int i = 0; i < testKey.length; i++) {
            testHashMap.put(testKey[i], testValue[i]);
            validHashMap.put(testKey[i], testValue[i]);
        }
        HashMap<A, Avalue> tmpHashMap = new HashMap<>();
        testHashMap.entryArray().forEach((entry) -> tmpHashMap.put(entry.getKey(), entry.getValue()));
        assert (tmpHashMap.equals(validHashMap));
    }

    @Test
    public void testReplace() {
        assert (testHashMap.containsKey(testKey[0]));
        Avalue oldVal = testHashMap.get(new A(1));
        assert (testHashMap.replace(new A(1), new Cvalue()).equals(oldVal));
    }

    @Test
    public void testRemove() {
        assert (testHashMap.remove(testKey[0]));
        assert (!testHashMap.containsKey(testKey[0]));
        validHashMap.remove(testKey[0]);
        assert (testHashMap.size() == validHashMap.size());
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

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return Integer.toString(id);
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

    static class Avalue {
        String name = "Avalue";

        public Avalue(String name) {
            this.name = name;
        }

        public Avalue() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Avalue avalue = (Avalue) o;
            return Objects.equals(name, avalue.name);
        }

        @Override
        public int hashCode(){
            return Objects.hash(name);
        }
        @Override
        public String toString() {
            return name;
        }
    }

    static class Bvalue extends Avalue {
        public Bvalue() {
            super("Bvalue");
        }
    }

    static class Cvalue extends Avalue {
        public Cvalue() {
            super("Cvalue");
        }
    }
}