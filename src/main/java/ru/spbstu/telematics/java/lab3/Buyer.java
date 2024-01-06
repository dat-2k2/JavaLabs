package ru.spbstu.telematics.java.lab3;
public abstract class Buyer extends Thread {
    final Cashier cashier;
    String nameBuyer;

    public Buyer(String name, Cashier cashier) {
        this.nameBuyer = name;
        this.cashier = cashier;
    }

    public abstract boolean toQueue();

    @Override
    public void run() {
        if (!toQueue()) {
            System.out.println("[" + nameBuyer + "]: Queue full");
            return;
        }

        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                this.interrupt();
                System.out.println(nameBuyer + " is interrupted");
            }
        }

        //request order
        System.out.println(this.nameBuyer + " get cheese");

    }

    @Override
    public String toString() {
        return this.nameBuyer;
    }
}
