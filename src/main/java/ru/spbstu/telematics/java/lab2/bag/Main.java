package ru.spbstu.telematics.java.lab2.bag;

import ru.spbstu.telematics.java.lab2.list.MyList;
import ru.spbstu.telematics.java.lab2.list.arraylist.MyArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){

        //TODO: Add REPL
        System.out.println("Interactive example of MyBag");
        MyBag<String> myHashBag = new MyHashBag<>(Arrays.asList("Apple", "Banana", "Orange", "Apple", "Apple", "Orange"));
        System.out.println();

        System.out.println("This is a Bag");
        System.out.println(myHashBag);

        System.out.println("Add to the bag: ");

        myHashBag.add("Apple");
        System.out.println(myHashBag);
        System.out.println();

        System.out.println("Remove Banana from the bag");
        myHashBag.remove("Banana");
        System.out.println(myHashBag);
        System.out.println();

        System.out.println("Remove 2 Apples from the bag");
        myHashBag.remove("Apple", 2);
        System.out.println(myHashBag);

        System.out.println("Get the counts of Orange");
        System.out.println(myHashBag.getCount("Orange"));

        System.out.println("Get the counts of Banana");
        System.out.println(myHashBag.getCount("Banana"));

    }
}
