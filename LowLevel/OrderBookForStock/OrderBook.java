/**
 * Problem : In-Memory Order Matching Engine (OOD & Data Structures Focus)
 * Design the core matching engine for a single stock ticker (e.g., "MSFT").
 *
 * Requirements:
 *
 * placeOrder(String orderId, String side, double price, int quantity): Accepts a new Buy or Sell order.
 *
 * Matching Rules (Price-Time Priority):
 *
 * Buy orders are matched with the lowest available Sell price.
 *
 * Sell orders are matched with the highest available Buy price.
 *
 * If multiple orders exist at the same best price, the order that arrived first (FIFO) is matched first.
 *
 * Partial Fills: If an incoming order has a quantity of 100, but the best opposite order only has 40, the engine should execute 40 and leave the remaining 60 in the order book to match with the next best price.
 *
 * Constraints:
 *
 * Focus on the algorithmic efficiency of finding the best price and maintaining FIFO queues for ties.
 *
 * Explain the Big-O time complexity for placing an order and executing a trade.
 *
 *  make this engine thread-safe for high-frequency concurrent inputs.
 */
package LowLevel.OrderBookForStock;



import LowLevel.OrderBookForStock.Constants.OrderType;

import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

public class OrderBook {
    // We could use priority queue but as we need to remove the orders then
    //In Java, PriorityQueue does not expose a heapify method to re-sort after an internal modification.
    // To update or remove a specific order in a Java PriorityQueue,
    // it must first find the element using a linear scan,
    // making the removal $O(N)$, not $O(\log N)$.
    // For a high-frequency trading engine, an $O(N)$ operation on every partial fill or cancellation is a fatal bottleneck.

    //better group the orders based on their price
    private final TreeMap<Long, Queue<Order>> sellOrder = new TreeMap<>();
    private final TreeMap<Long,Queue<Order>> buyOrder = new TreeMap<>();

    private final ReentrantLock lock = new ReentrantLock();

    public void placeOrder(Order newOrder) {

        try {
                if (newOrder.getOrderType() == OrderType.BUY) {
                    matchBuyOrder(newOrder);
                } else {
                    matchSellOrder(newOrder);
                }
            } finally {
                lock.unlock();
            }


    }

    private void matchBuyOrder(Order newOrder) {
        long  quantity = newOrder.getQuantity();
        Long price = buyOrder.firstEntry().getKey();
        Queue<Order> orderQueue = buyOrder.firstEntry().getValue();

        int size = orderQueue.size();
        while(size > 0) {
            Order currOrder = orderQueue.peek();
            size--;
            if(currOrder.getQuantity() >= quantity) {
                currOrder.setQuantity(currOrder.getQuantity()-quantity);
                orderQueue.offer(currOrder);
                orderQueue.offer(newOrder);
                break;
            }
            buyOrder.put(price,orderQueue);
        }
    }

    private void matchSellOrder(Order newOrder) {
        long  quantity = newOrder.getQuantity();
        Long price = sellOrder.lastEntry().getKey();
        Queue<Order> orderQueue = sellOrder.firstEntry().getValue();

        int size = orderQueue.size();
        while(size > 0) {
            Order currOrder = orderQueue.peek();
            size--;
            if(currOrder.getQuantity() >= quantity) {
                currOrder.setQuantity(currOrder.getQuantity()-quantity);
                orderQueue.offer(currOrder);
                orderQueue.offer(newOrder);
                break;
            }

        }
        sellOrder.put(price,orderQueue);
    }


}
