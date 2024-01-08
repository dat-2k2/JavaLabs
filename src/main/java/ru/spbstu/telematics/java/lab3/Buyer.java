package ru.spbstu.telematics.java.lab3;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * Abstract class for Buyer. Buyer comes to queue, request order and finished after being served
 */
public abstract class Buyer extends Thread {
    static final int TIME_WAIT = 1000;
    String nameBuyer;
    boolean isYourTurn;
    boolean isServed;

    BlockingDeque<Buyer> queue;

    /**
     * Constructor for Buyer
     * @param name name of Buyer
     * @param queue the reference to the queue that this Buyer will be added to.
     */
    public Buyer(String name, BlockingDeque<Buyer> queue) {
        this.nameBuyer = name;
        this.isYourTurn = false;
        this.isServed = false;
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

        waitTillTurn();
        //request order
        System.out.println(this.nameBuyer + " get cheese");
        waitTillServed();
        System.out.println(this.nameBuyer + " cheese");

    }

    synchronized void waitTillTurn(){
        while (!isYourTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                this.interrupt();
                System.out.println(nameBuyer + " is interrupted");
            }
        }
    }

    synchronized void waitTillServed(){
        while (!isServed) {
            try {
                wait();
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


    public void setYourTurn(boolean yourTurn) {
        isYourTurn = yourTurn;
    }
    public void setServed(boolean Served) {
        isServed = Served;
    }

}
