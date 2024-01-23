package ru.spbstu.telematics.java.lab3;
import java.util.Deque;
/**
 * Utility Class for Cashier. Cashier eventually serve the first in queue, while new Buyers are added to queue concurrently.
 */
public class Cashier{
    static final int TIME_SERVE = 1000;
    /**
     * Cashier continuously serve the first in queue
     * @param allBuyer the queue containing all Buyers
     */
    public static void run(Deque<Buyer> allBuyer){
        while(true){
            try {
                Buyer currentBuyer;
                System.out.println("Current queue " + allBuyer);

                //get the first Buyer
                currentBuyer = allBuyer.pollFirst();
                // serve
                sell(currentBuyer);
            }
            catch (NullPointerException e){
                System.out.println(e.getMessage());
                waiting(TIME_SERVE);
            }
        }
    }

    protected static void waiting(int time)
    {
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


    /**
     * Sell to a buyer
     * @param b - buyer to serve
     * @throws NullPointerException if b is null
     */
    public static void sell(Buyer b) throws NullPointerException
    {
        if (b == null)
            throw new NullPointerException("Null buyer");

        System.out.println("Serving "+b.nameBuyer);

        //your turn
        b.setYourTurn(true);
        waiting(TIME_SERVE);

        //serving
        b.setServed(true);

        //wait till the Buyer exit before serving another
        try {
            b.join();
        } catch (InterruptedException e) {
            System.out.println("Buyer "+ b.nameBuyer +" is interrupted");
        }
    }


}
