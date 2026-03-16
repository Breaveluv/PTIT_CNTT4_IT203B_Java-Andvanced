package ordermanagement.model;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private Product product;
    private int quantity;
    private double unitPrice;

    public OrderItem(Product product, int quantity, double unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotal() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return String.format("OrderItem{product='%s', quantity=%d, unitPrice=%.2f, total=%.2f}",
                product.getName(), quantity, unitPrice, getTotal());
    }
}