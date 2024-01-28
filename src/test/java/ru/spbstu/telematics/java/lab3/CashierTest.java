package ru.spbstu.telematics.java.lab3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Test for lab 3
 */
public class CashierTest {
    Deque<Buyer> allBuyer = new ConcurrentLinkedDeque<>();
    Buyer hb1 = new CalmBuyer("Calm 1", allBuyer);
    Buyer hb2 = new CalmBuyer("Calm 2", allBuyer);
    Buyer bb1 = new HurryBuyer("Hurry 1", allBuyer);
    Buyer bb2 = new HurryBuyer("Hurry 2", allBuyer);

    @Test
    public void testAddBuyer() {
        hb1.toQueue(allBuyer);
        bb1.toQueue(allBuyer);
        hb2.toQueue(allBuyer);
        bb2.toQueue(allBuyer);

        assert (allBuyer.pollFirst().nameBuyer.equals(bb2.nameBuyer));
        assert (allBuyer.pollFirst().nameBuyer.equals(bb1.nameBuyer));
        assert (allBuyer.pollFirst().nameBuyer.equals(hb1.nameBuyer));
        assert (allBuyer.pollFirst().nameBuyer.equals(hb2.nameBuyer));

    }

    /**
     * Test order of buyers in queue
     */
    @Test
    public void testRun() {

        hb1.start();
        hb2.start();
        bb1.start();
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
    static class CashierWithLog extends Cashier {
        static ArrayList<Class<? extends Buyer>> log = new ArrayList<>();

        /**
         * Test serving with a queue of 2 HurryBuyer and 2 CalmBuyer
         *
         * @param allBuyer the queue to test
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
                    log.add(currentBuyer.getClass());
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    if (tryWaitingNewBuyer == 5) {
                        System.out.println("No new Buyer. Exit...");
                        return;
                    }
                    System.out.println("Queue empty. Try waiting new Buyer: " + (tryWaitingNewBuyer + 1));
                    waiting(TIME_SERVE);
                    tryWaitingNewBuyer++;
                }
            }

        }
    }
}

