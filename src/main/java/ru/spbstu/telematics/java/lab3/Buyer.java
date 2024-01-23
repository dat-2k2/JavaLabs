package ru.spbstu.telematics.java.lab3;

import java.util.concurrent.BlockingDeque;

/**
 * Abstract class for Buyer. Buyer comes to queue, request order and finished after being served
 */
public abstract class Buyer extends Thread {
    static final int TIME_WAIT = 1000;
    String nameBuyer;
    State isYourTurn;
    State isServed;

    BlockingDeque<Buyer> queue;

    /**
     * Constructor for Buyer
     * @param name name of Buyer
     * @param queue the reference to the queue that this Buyer will be added to.
     */
    public Buyer(String name, BlockingDeque<Buyer> queue) {
        this.nameBuyer = name;
        this.isYourTurn = new State(false);
        this.isServed = new State(false);
        this.queue = queue;

    }

    /**
     * Behaviour of Buyer when entering queue.
     * @param queue add the Buyer to this queue. If the queue is full, try to add during an amount of time.
     * @return true if the Buyer was successfully added to queue
     */
    public abstract boolean toQueue(BlockingDeque<Buyer> queue);

    /**
     * After being added to queue, Buyer wait until Cashier wakes them up. They request order by "get cheese"
     */
    @Override
    public void run() {
        //come to the queue
        if (!toQueue(queue)) {
            System.out.println("[" + nameBuyer + "]: Queue full");
            return;
        }

        //request order
        waitTill(this.isYourTurn);
        System.out.println(this.nameBuyer + " get cheese");

        //be served
        waitTill(this.isServed);
        System.out.println(this.nameBuyer + " cheese");

    }

    /**
     * Wrapped class for Boolean, used to pass-by-reference
     */
    static class State{
        public State(boolean prop) {
            this.prop = prop;
        }
        boolean prop;
    }

    private synchronized void waitTill(State state){
        while (!state.prop) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                this.interrupt();
                System.out.println(nameBuyer + " is interrupted");
            }
        }
    }

    /**
     * Name of this Buyer
     * @return Name of this Buyer
     */
    @Override
    public String toString() {
        return this.nameBuyer;
    }

    public synchronized void setYourTurn(boolean yourTurn) {
        isYourTurn.prop = yourTurn;
        this.notify();
    }
    public synchronized void setServed(boolean Served) {
        isServed.prop = Served;
        this.notify();
    }

}
