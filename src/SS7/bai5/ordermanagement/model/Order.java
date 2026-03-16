package ordermanagement.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private String orderId;
    private Customer customer;
    private List<OrderItem> items;
    private LocalDateTime orderDate;
    private String status;
    private double subtotal;
    private double discountAmount;
    private double total;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
        this.status = "PENDING";
        this.subtotal = 0;
        this.discountAmount = 0;
        this.total = 0;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getTotal() {
        return total;
    }

    public void addItem(OrderItem item) {
        items.add(item);
        updateTotals();
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
        updateTotals();
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
        updateTotals();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private void updateTotals() {
        this.subtotal = items.stream().mapToDouble(OrderItem::getTotal).sum();
        this.total = subtotal - discountAmount;
    }

    @Override
    public String toString() {
        return String.format("Order{id='%s', customer='%s', items=%d, status='%s', total=%.2f}",
                orderId, customer.getName(), items.size(), status, total);
    }
}