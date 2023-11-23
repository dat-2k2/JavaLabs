package ru.spbstu.telematics.java.lab2.bag;
import ru.spbstu.telematics.java.lab2.MyIterable;
import ru.spbstu.telematics.java.lab2.MyIterator;
import ru.spbstu.telematics.java.lab2.arraylist.MyArrayList;

import java.util.NoSuchElementException;

/**
 * multiset using array implementation
 * Practically better use hash table or binary tree
 * Null is not counted
 * @param <Item>
 */
public class MyBag<Item> implements MyBagInterface<Item> {
    int sizeBag;
    MyArrayList<MyBagItem<Item>> listItem;

    static class MyBagItem<Item>{
        Item item;
        Integer occur;

        public MyBagItem(Item i, Integer o) throws NullPointerException{
            if (i == null)
                throw new NullPointerException();
            item = i;
            occur = o;
        }

        public MyBagItem(Item i) throws NullPointerException{
            if (i == null)
                throw new NullPointerException();
            item = i;
            occur = 1;
        }

        @Override
        public boolean equals(Object other) {
            return item.equals(other);
        }

    }
    public MyBag(){
        sizeBag = 0;
        listItem = new MyArrayList<>();
    }

    public MyBag(MyIterable<? extends Item> collection) throws NullPointerException{
        if (collection == null)
            throw new NullPointerException();

        sizeBag = collection.size();
        listItem = new MyArrayList<>();
        collection.forEach(e ->{
            int pos = listItem.indexOf(new MyBagItem<Item>(e));
            if (pos > -1){
                listItem.get(pos).occur++;
            }
            else {
                listItem.add(new MyBagItem<Item>(e, 1));
            }
        });
    }


    public MyBag(Item[] arr) throws NullPointerException{
        if (arr == null)
            throw new NullPointerException();

        sizeBag = arr.length;
        listItem = new MyArrayList<>();
        for (Item e: arr){
            int pos = listItem.indexOf(e);
            if (pos > -1){
                listItem.get(pos).occur++;
            }
            else {
                listItem.add(new MyBagItem<Item>(e,1));
            }
        }
    }
    @Override
    public MyIterator<Item> iterator() {
        return new MyIterator<Item>() {
            int index;
            @Override
            public boolean hasNext() {
                return index < listItem.size();
            }

            @Override
            public Item next() throws NoSuchElementException {
                if (!hasNext())
                    throw new NoSuchElementException();
                return listItem.get(index++).item;
            }
        };
    }

    @Override
    public int size() {
        return sizeBag;
    }

    @Override
    public boolean contains(Object item) {
        return listItem.contains(item);
    }

    @Override
    public int howManyItems() {
        return listItem.size();
    }

    @Override
    public boolean isEmpty() {
        return listItem.isEmpty();
    }


    @Override
    public void add(Item element) {
        int pos = listItem.indexOf(element);
        if (pos>-1){
            listItem.set(pos, new MyBagItem<>(element, listItem.get(pos).occur+1));
        }
        else{
            listItem.add(new MyBagItem<Item>(element,1));
        }

        sizeBag++;
    }

    @Override
    public boolean remove(Item element) {
        int pos = listItem.indexOf(element);
        if (pos > -1){
            int o = listItem.get(pos).occur;
            if (o > 1)
                listItem.get(pos).occur--;
            else{
                listItem.remove(pos);
            }
            sizeBag--;
            return true;
        }
        return false;
    }

    @Override
    public Integer getOccur(Item element) {
        int pos = listItem.indexOf(element);
        if (pos > -1)
            return listItem.get(pos).occur;
        else return 0;
    }

    @Override
    public MyArrayList<Item> items() {
        MyArrayList<Item> res = new MyArrayList<>(listItem.size());
        listItem.forEach(bagItem -> {
            res.add(bagItem.item);
        });
        return res;
    }
}