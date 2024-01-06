package ru.spbstu.telematics.java.lab3;

public class BraveBuyer extends Buyer{

    public BraveBuyer(String name, Cashier cashier) {
        super(name, cashier);
    }

    @Override
    public boolean toQueue() {
        return cashier.allBuyer.offerFirst(this);
    }
}