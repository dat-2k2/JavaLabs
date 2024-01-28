package ru.spbstu.telematics.java.lab3;
import java.util.Deque;

/**
 * Utility Class for Cashier. Cashier eventually serve the first in queue, while new Buyers are added to queue concurrently.
 */
public class Cashier {
    static final int TIME_SERVE = 1000;
    private static final int MAX_TRY_WAITING_NEW_BUYER = 5;

    /**
     * Cashier continuously serve the first in queue
     *
     * @param allBuyer the queue containing all Buyers
     */
    public static void run(Deque<Buyer> allBuyer) {
        int tryWaitingNewBuyer = 0;
        while (true) {
            try {
                Buyer currentBuyer;
                System.out.println("Current queue " + allBuyer);

                //get the first Buyer
                currentBuyer = allBuyer.pollFirst();
                // serve
                sell(currentBuyer);
                if (tryWaitingNewBuyer > 0)
                    tryWaitingNewBuyer = 0;
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
                if (tryWaitingNewBuyer == MAX_TRY_WAITING_NEW_BUYER) {
                    System.out.println("No new Buyer. Exit...");
                    return;
                }
                System.out.println("Queue empty. Try waiting new Buyer: " + (tryWaitingNewBuyer + 1));
                waiting(TIME_SERVE);
                tryWaitingNewBuyer++;
            }
        }
    }

    protected static void waiting(int time) {
        try {
            Thread tmp = new Thread() {
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
     *
     * @param b - buyer to serve
     * @throws NullPointerException if b is null
     */
    public static void sell(Buyer b) throws NullPointerException {
        if (b == null)
            throw new NullPointerException("Null buyer");

        System.out.println("Serving " + b.nameBuyer);

        //your turn
        b.setYourTurn(true);
        waiting(TIME_SERVE);

        //serving
        b.setServed(true);

        //wait till the Buyer exit before serving another
        try {
            b.join();
        } catch (InterruptedException e) {
            System.out.println("Buyer " + b.nameBuyer + " is interrupted");
        }
    }


}
