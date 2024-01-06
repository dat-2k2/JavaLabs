package ru.spbstu.telematics.java.lab3;

import org.junit.Test;

import java.util.ArrayList;

public class CashierTest {
    CashierWithLog testCashier = new CashierWithLog();
    Buyer hb1 = new HumbleBuyer("Humber 1", testCashier);
    Buyer hb2 = new HumbleBuyer("Humber 2", testCashier);
    Buyer bb1 = new BraveBuyer("Brave 1", testCashier);
    Buyer bb2 = new BraveBuyer("Brave 2", testCashier);
    @Test
    public void testRun() {
        testCashier.start();
        hb1.start();
        bb1.start();
        hb2.start();
        bb2.start();

    }

    static class CashierWithLog extends Cashier{
        ArrayList<Class> log = new ArrayList<>();
        @Override
        public void sellCheese(Buyer buyer){
            this.log.add(buyer.getClass());
            synchronized (this) {
                try {
                    wait(TIME_SERVE);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
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
                        break;
                    }
                }
            }
            assert (log.get(0).equals(BraveBuyer.class));
            assert (log.get(1).equals(BraveBuyer.class));
            assert (log.get(2).equals(HumbleBuyer.class));
            assert (log.get(3).equals(HumbleBuyer.class));
        }
    }
}

