package ru.spbstu.telematics.java.lab3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Test for lab 3
 */
public class CashierTest {
    BlockingDeque<Buyer> allBuyer = new LinkedBlockingDeque<>(10);
    Buyer hb1 = new CalmBuyer("Calm 1", allBuyer);
    Buyer hb2 = new CalmBuyer("Calm 2", allBuyer);
    Buyer bb1 = new HurryBuyer("Hurry 1", allBuyer);
    Buyer bb2 = new HurryBuyer("Hurry 2", allBuyer);

    /**
     * Test order of buyers in queue
     */
    @Test
    public void testRun() {
        hb1.start();
        bb1.start();
        hb2.start();
        bb2.start();

        CashierWithLog.run(allBuyer);

        assert (CashierWithLog.log.get(0).equals(HurryBuyer.class));
        assert (CashierWithLog.log.get(1).equals(HurryBuyer.class));
        assert (CashierWithLog.log.get(2).equals(CalmBuyer.class));
        assert (CashierWithLog.log.get(3).equals(CalmBuyer.class));
    }

    /**
     * This class extends Cashier and add log feature to test the serving order.
     */
    static class CashierWithLog extends Cashier{
        static ArrayList<Class> log = new ArrayList<>();
        private static int takes = 0;

        /**
         * Test serving with a queue of 2 HurryBuyer and 2 CalmBuyer
         * @param allBuyer the queue to test
         */
        public static void run(BlockingDeque<Buyer> allBuyer){
            while(true){
                try {
                    Buyer currentBuyer;
                    System.out.println("Current queue " + allBuyer);

                    //get the first Buyer
                    currentBuyer = allBuyer.pollFirst();

                    // serve
                    sell(currentBuyer);
                    log.add(currentBuyer.getClass());
                    if (takes > 0)
                        takes = 0;
                }
                catch (NullPointerException e){
                    System.out.println(e.getMessage());
                    if (takes == 3)
                        return;
                    takes++;
                }
            }

        }
    }
}

