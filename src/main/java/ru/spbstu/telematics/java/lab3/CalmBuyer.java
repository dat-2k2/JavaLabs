package ru.spbstu.telematics.java.lab3;

import java.util.Deque;

/**
 * Calm Buyer come after the end of queue, they will wait till their turn
 */
public class CalmBuyer extends Buyer {

    /**
     * {@inheritDoc}
     */
    public CalmBuyer(String name, Deque<Buyer> queue) {
        super(name, queue);
    }

    /**
     * {@inheritDoc}.
     * Calm Buyer comes after the end of queue.
     */
    @Override
    public boolean toQueue(Deque<Buyer> queue) {
        return queue.offerLast(this);
    }


}
