package ordermanagement.strategy.impl;

import ordermanagement.model.Order;
import ordermanagement.strategy.DiscountStrategy;

public class FixedDiscount implements DiscountStrategy {
    private double amount;

    public FixedDiscount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Discount amount cannot be negative");
        }
        this.amount = amount;
    }

    @Override
    public double calculateDiscount(Order order) {
        double discount = Math.min(amount, order.getSubtotal());
        return discount;
    }

    @Override
    public String getStrategyName() {
        return String.format("Fixed Discount (VND %.2f)", amount);
    }
}