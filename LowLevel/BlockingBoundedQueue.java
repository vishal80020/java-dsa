package LowLevel;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingBoundedQueue<T> {

    private Queue<T> blockingBoundedQueue;
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private final AtomicInteger size ;

    public  BlockingBoundedQueue(int capacity) {
        this.blockingBoundedQueue = new LinkedList<>();
        this.capacity = capacity;
        this.size = new AtomicInteger();
    }

    public void enqueue(T item) {
        try {
            lock.lock();
            while(blockingBoundedQueue.size() == capacity) {
                //queue is full so just put this thread into notFull waiting room
                notFull.await();
            }

            // queue is not full so insert the item and inform consumer to consume
            blockingBoundedQueue.offer(item);
            size.getAndIncrement();
            notEmpty.signal();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public T consume() {

        try {
            lock.lock();
            while(blockingBoundedQueue.isEmpty()) {

                //queue is empty so put it into notEmpty wating room
                notEmpty.await();
            }

            T item = blockingBoundedQueue.poll();
            size.getAndDecrement();
            // just notify producer to produce
            notFull.signal();
            return item;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return size.get();
    }

}
