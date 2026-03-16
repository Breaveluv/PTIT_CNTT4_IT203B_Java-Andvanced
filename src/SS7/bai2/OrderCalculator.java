package SS7.bai2;

public class OrderCalculator {
    private DiscountStrategy discountStrategy;

    public OrderCalculator(DiscountStrategy strategy) {
        this.discountStrategy = strategy;
    }

    public void setDiscountStrategy(DiscountStrategy strategy) {
        this.discountStrategy = strategy;
    }

    public double calculateFinalPrice(double totalAmount) {
        return discountStrategy.applyDiscount(totalAmount);
    }
}
