/**
 * Problem : Bounded Blocking Queue (Concurrency Focus)
 * Design and implement a thread-safe, bounded blocking queue from scratch.
 *
 * Requirements:
 *
 * enqueue(item): Adds an item to the back of the queue. If the queue is at capacity, the calling thread must block until space becomes available.
 *
 * dequeue(): Removes and returns the item at the front of the queue. If the queue is empty, the calling thread must block until an item is available.
 *
 * size(): Returns the current number of elements.
 *
 * Constraints:
 *
 * You cannot use any built-in concurrent collections (like ArrayBlockingQueue or LinkedBlockingQueue).
 *
 * You cannot use the synchronized keyword. You must use explicit locks (e.g., ReentrantLock and Condition variables).
 *
 * Your solution must explicitly handle thread interruption and spurious wakeups.
 */


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
