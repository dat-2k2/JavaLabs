package ru.spbstu.telematics.java.lab2.list;
import ru.spbstu.telematics.java.lab2.list.arraylist.MyArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){

        MyList<String> arrayList = new MyArrayList<>(Arrays.asList("Apple", "Banana", "Orange", "Pineapple", "Tomato", "Peach", "Blossom"));
        System.out.println("This is an array list");
        System.out.println(arrayList);

        System.out.println("Add Apple to the end of list");
        arrayList.add("Apple");
        System.out.println(arrayList);

        System.out.println("Add Orange to position 1 of the list");
        arrayList.add(1, "Orange");
        System.out.println(arrayList);

        System.out.println("Remove Banana from the list");
        arrayList.remove("Banana");
        System.out.println(arrayList);

        System.out.println("Change the element at position 3 to Banana");
        System.out.println("The old value" + arrayList.set(3, "Banana"));
        System.out.println(arrayList);

        System.out.println("Get the element at position 0 from list");
        System.out.println(arrayList.get(0));

        //Linked List
        MyList<String> LinkedList = new MyArrayList<>(Arrays.asList("Green", "Pink", "Yellow", "Red", "Green", "White", "Black"));
        System.out.println("This is an array list");
        System.out.println(LinkedList);

        System.out.println("Add Gray to the end of list");
        LinkedList.add("Gray");
        System.out.println(LinkedList);

        System.out.println("Add Black to position 1 of the list");
        LinkedList.add(1, "Black");
        System.out.println(LinkedList);

        System.out.println("Remove Pink from the list");
        LinkedList.remove("Pink");
        System.out.println(LinkedList);

        System.out.println("Change the element at position 3 to White");
        System.out.println("The old value" + LinkedList.set(3, "White"));
        System.out.println(LinkedList);

        System.out.println("Get the element at position 0 from list");
        System.out.println(LinkedList.get(0));
    }
}
