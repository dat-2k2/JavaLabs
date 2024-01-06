package ru.spbstu.telematics.java.lab3;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Class for Cashier. Cashier eventually serve the first in queue, while new Buyers are added to queue concurrently.
 */
public class Cashier extends Thread{
    final BlockingDeque<Buyer> allBuyer;
    static final int TIME_SERVE = 1000;
    public Cashier() {
        this.allBuyer = new LinkedBlockingDeque<>(100);
    }

    /**
     * Cashier continuously serve the first in queue
     */
    @Override
    public void run(){
        while(true){
            if (!allBuyer.isEmpty()) {
                sell();
            }
            else {
                synchronized (this) {
                    try {
                        wait(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (allBuyer.isEmpty()){
                    return;
                }
            }
        }
    }

    void sell(){
        Buyer currentBuyer;
        System.out.println("Current queue " + allBuyer);
        currentBuyer = allBuyer.pollFirst();

        //Buyer can join the queue while Cashier is selling so no sync here.
        if (currentBuyer != null){
            //wake it up
            synchronized (currentBuyer){
                currentBuyer.setYourTurn(true);
                currentBuyer.notifyAll();
            }
            //sell it
            sellCheese(currentBuyer);
        }
    }

    //Only sell to one customer at a time
     synchronized void sellCheese(Buyer buyer){
        try {
            wait(TIME_SERVE);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(buyer.nameBuyer + " cheese");
    }
}
