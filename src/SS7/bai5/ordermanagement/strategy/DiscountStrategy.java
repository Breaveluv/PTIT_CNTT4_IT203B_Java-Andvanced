package ordermanagement.strategy;

import ordermanagement.model.Order;

public interface DiscountStrategy {
    double calculateDiscount(Order order);

    String getStrategyName();
}