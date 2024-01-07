package ru.spbstu.telematics.java.lab3;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Calm Buyer come after the end of queue, they will wait till their turn
 */
public class CalmBuyer extends Buyer{

    public CalmBuyer(String name, BlockingDeque<Buyer> queue) {
        super(name, queue);
    }

    /**
     * {@inheritDoc}
     * Calm Buyer comes after the end of queue
     */
    @Override
    public boolean toQueue(BlockingDeque<Buyer> queue) {
        try {
            return queue.offerLast(this, TIME_WAIT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println(nameBuyer + " is interrupted");
            return false;
        }
    }



}
