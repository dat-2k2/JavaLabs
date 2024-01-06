package ru.spbstu.telematics.java.lab3;

/**
 * Abstract class for Buyer
 */
public abstract class Buyer extends Thread {
    final Cashier cashier;
    String nameBuyer;
    boolean isYourTurn;
    public Buyer(String name, Cashier cashier) {
        this.nameBuyer = name;
        this.cashier = cashier;
        this.isYourTurn = false;
    }

    /**
     * Behaviour of Buyer when entering queue
     * @return true if the Buyer was successfully added to queue
     */
    public abstract boolean toQueue();

    /**
     * After being added to queue, Buyer wait until Cashier wakes them up. They request order by "get cheese"
     */
    @Override
    public void run() {
        //come to the queue
        if (!toQueue()) {
            System.out.println("[" + nameBuyer + "]: Queue full");
            return;
        }
        waitTillTurn();

        //request order
        System.out.println(this.nameBuyer + " get cheese");

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


    @Override
    public String toString() {
        return this.nameBuyer;
    }

    public void setYourTurn(boolean yourTurn) {
        isYourTurn = yourTurn;
    }

}
