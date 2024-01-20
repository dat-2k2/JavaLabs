package ru.spbstu.telematics.java.lab3;

import java.util.concurrent.BlockingDeque;


/**
 * Class for Cashier. Cashier eventually serve the first in queue, while new Buyers are added to queue concurrently.
 */
public class Cashier{
    static final int TIME_SERVE = 1000;
    /**
     * Cashier continuously serve the first in queue
     * @param allBuyer the queue containing all Buyers
     */
    public static void run(BlockingDeque<Buyer> allBuyer){
        while(true){
            if (!allBuyer.isEmpty()) {
                sell(allBuyer);
            }
            else {
                waiting(TIME_SERVE);
                if (allBuyer.isEmpty()){
                    return;
                }
            }
        }
    }

    static void waiting(int time){
        try {
            Thread tmp = new Thread(){
                @Override
                public synchronized void run() {
                    try {
                        wait(time);
                    } catch (InterruptedException e) {
                        System.out.println("Cashier is interrupted");
                    }
                }
            };
            tmp.start();
            tmp.join();
        } catch (InterruptedException e) {
            System.out.println("Cashier is interrupted");
        }
    }


    static void sell(BlockingDeque<Buyer> allBuyer){
        Buyer currentBuyer;
        System.out.println("Current queue " + allBuyer);
        currentBuyer = allBuyer.pollFirst();

        //Buyer can join the queue while Cashier is selling so no sync here.
        if (currentBuyer != null){
            System.out.println("Serving "+currentBuyer.nameBuyer);
            //wake it up
            synchronized (currentBuyer){
                currentBuyer.setYourTurn(true);
                currentBuyer.notify();
                waiting(TIME_SERVE);
                currentBuyer.setServed(true);
                currentBuyer.notify();
                //wait till the Buyer exit before serving another
                try {
                    currentBuyer.join();
                } catch (InterruptedException e) {
                    System.out.println("Buyer "+ currentBuyer.nameBuyer +" is interrupted");
                }
            }
        }
    }
}
