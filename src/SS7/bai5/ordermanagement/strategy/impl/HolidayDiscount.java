package ordermanagement.strategy.impl;

import ordermanagement.model.Order;
import ordermanagement.strategy.DiscountStrategy;
import java.time.LocalDateTime;

public class HolidayDiscount implements DiscountStrategy {
    private double percentage;

    public HolidayDiscount(double percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        }
        this.percentage = percentage;
    }

    @Override
    public double calculateDiscount(Order order) {
        if (isHoliday()) {
            return order.getSubtotal() * (percentage / 100.0);
        }
        return 0;
    }

    @Override
    public String getStrategyName() {
        return String.format("Holiday Discount (%.1f%% if applicable)", percentage);
    }

    private boolean isHoliday() {
        LocalDateTime now = LocalDateTime.now();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        // Vietnamese holidays
        if ((month == 1 && day == 1) || // New Year
                (month == 2 && day == 9) || // National Unity Day
                (month == 4 && day == 18) || // Hung Kings' Temple Festival
                (month == 9 && day == 2) || // National Day
                (month == 12 && day == 25)) { // Christmas
            return true;
        }
        return false;
    }
}