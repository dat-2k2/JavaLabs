package ru.spbstu.telematics.java.lab3;

/**
 * HurryBuyer, which will come to the head of queue
 */
public class HurryBuyer extends Buyer{
    public HurryBuyer(String name, Cashier cashier) {
        super(name, cashier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQueue() {
        return cashier.allBuyer.offerFirst(this);
    }
}