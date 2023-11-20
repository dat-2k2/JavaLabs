package ru.spbstu.telematics.java.lab2;
import java.util.function.Consumer;

public interface MyIterable<T> extends Lab2Interface<T>{
    default void forEach(Consumer<? super T> action){
        for (MyIterator<T> i = iterator(); i.hasNext();){
            action.accept(i.next());
        }
    }
    MyIterator<T> iterator();
}
