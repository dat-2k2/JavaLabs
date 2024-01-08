package ru.spbstu.telematics.java.lab3;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * HurryBuyer, which will come to the head of queue
 */
public class HurryBuyer extends Buyer{
    /**
     * {@inheritDoc}
     */
    public HurryBuyer(String name, BlockingDeque<Buyer> queue) {
        super(name, queue);
    }

    /**
     * {@inheritDoc}
     * Calm Buyer comes after the end of queue
     */
    @Override
    public boolean toQueue(BlockingDeque<Buyer> queue) {
        try {
            return queue.offerFirst(this, TIME_WAIT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println(nameBuyer + " is interrupted");
            return false;
        }
    }
}