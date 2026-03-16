package ordermanagement.strategy.impl;

import ordermanagement.model.Order;
import ordermanagement.strategy.DiscountStrategy;

public class PercentageDiscount implements DiscountStrategy {
    private double percentage;

    public PercentageDiscount(double percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        }
        this.percentage = percentage;
    }

    @Override
    public double calculateDiscount(Order order) {
        return order.getSubtotal() * (percentage / 100.0);
    }

    @Override
    public String getStrategyName() {
        return String.format("Percentage Discount (%.1f%%)", percentage);
    }
}