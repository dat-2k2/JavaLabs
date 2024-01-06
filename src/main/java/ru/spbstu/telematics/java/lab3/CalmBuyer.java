package ru.spbstu.telematics.java.lab3;

/**
 * Calm Buyer come after the end of queue, they will wait till their turn
 */
public class CalmBuyer extends Buyer{
    public CalmBuyer(String name, Cashier cashier) {
        super(name, cashier);
    }

    /**
     * {@inheritDoc}
     * Calm Buyer comes after the end of queue
     */
    @Override
    public boolean toQueue() {
        return cashier.allBuyer.offerLast(this);
    }
}
