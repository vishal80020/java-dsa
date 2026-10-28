package LowLevel.OrderBookForStock;

import LowLevel.OrderBookForStock.Constants.OrderType;

public class Order {

    private final String orderId;
    private final OrderType  orderType;

    // Use 'long' representing minor units (e.g., cents) instead of 'double'.
    // A price of $150.25 is stored as 15025. This prevents floating-point precision loss.
    private final Long price;

    // The exact moment the order hit the engine. Used for FIFO tie-breaking.
    private final long timestamp;

    // Mutable field: This will decrease as partial fills occur.
    private long quantity;

    public Order(String orderId,OrderType orderType,Long price,Long timestamp,Long quantity) {
        this.orderId = orderId;
        this.orderType = orderType;
        this.price = price;
        this.timestamp = timestamp;
        this.quantity = quantity;
    }

    public long getQuantity() {
        return quantity;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Long getPrice() {
        return price;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
