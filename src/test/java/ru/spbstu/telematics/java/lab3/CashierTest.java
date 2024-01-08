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

    }

    static class CashierWithLog extends Cashier{
        static ArrayList<Class> log = new ArrayList<>();
        public static void run(BlockingDeque<Buyer> allBuyer){
            while(true){
                if (!allBuyer.isEmpty()) {
                    log.add(allBuyer.getFirst().getClass());
                    sell(allBuyer);
                }
                else {
                    waiting(TIME_SERVE);
                    if (allBuyer.isEmpty()){
                        break;
                    }
                }
            }
            assert (log.get(0).equals(HurryBuyer.class));
            assert (log.get(1).equals(HurryBuyer.class));
            assert (log.get(2).equals(CalmBuyer.class));
            assert (log.get(3).equals(CalmBuyer.class));
        }
    }
}

