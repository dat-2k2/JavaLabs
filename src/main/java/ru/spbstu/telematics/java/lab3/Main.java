package ru.spbstu.telematics.java.lab3;

import java.util.Deque;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {
    public static void main(String[] args){
        Deque<Buyer> allBuyer = new ConcurrentLinkedDeque<>();
        Random rand = new Random(31);

        //continuously add new customer
        Thread buyerFactory = new Thread() {
            @Override
            public void run() {
                int c = 0;
                while (true){
                    int id = rand.nextInt();
                    if (id > 0){
                        new HurryBuyer("Hurry "+id%50, allBuyer).start();
                        new HurryBuyer("Hurry "+(id+1)%50, allBuyer).start();
                    }
                    else {
                        new CalmBuyer("Calm "+ (-id)%50, allBuyer).start();
                        new CalmBuyer("Calm "+ (-id+1)%50, allBuyer).start();

                    }
                    synchronized (this) {
                        try {
                            wait(Cashier.TIME_SERVE*2);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        };
        buyerFactory.start();
        Cashier.run(allBuyer);
    }
}