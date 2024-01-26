package ru.spbstu.telematics.java.lab3;


import java.util.Deque;

/**
 * HurryBuyer, which will come to the head of queue
 */
public class HurryBuyer extends Buyer{
    /**
     * {@inheritDoc}
     */
    public HurryBuyer(String name, Deque<Buyer> queue) {
        super(name, queue);
    }

    /**
     * {@inheritDoc}
     * Calm Buyer comes after the end of queue
     */
    @Override
    public boolean toQueue(Deque<Buyer> queue) {
        return queue.offerFirst(this);
    }
}