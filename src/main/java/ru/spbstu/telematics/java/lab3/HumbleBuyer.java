package ru.spbstu.telematics.java.lab3;

public class HumbleBuyer extends Buyer{
    public HumbleBuyer(String name, Cashier cashier) {
        super(name, cashier);
    }

    @Override
    public boolean toQueue() {
        return cashier.allBuyer.offerLast(this);
    }
}
