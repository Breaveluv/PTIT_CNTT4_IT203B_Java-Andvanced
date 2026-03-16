package SS7.bai2;

public class Main {
    public static void main(String[] args) {
        double totalAmount = 1000000;

        OrderCalculator calc = new OrderCalculator(new PercentageDiscount(10));
        System.out.println("Đơn hàng: tổng tiền 1.000.000, áp dụng PercentageDiscount 10%");
        System.out.println("Số tiền sau giảm: " + String.format("%.0f", calc.calculateFinalPrice(totalAmount)));

        calc.setDiscountStrategy(new FixedDiscount(50000));
        System.out.println("\nĐơn hàng: tổng tiền 1.000.000, áp dụng FixedDiscount 50.000");
        System.out.println("Số tiền sau giảm: " + String.format("%.0f", calc.calculateFinalPrice(totalAmount)));

        calc.setDiscountStrategy(new NoDiscount());
        System.out.println("\nĐơn hàng: tổng tiền 1.000.000, áp dụng NoDiscount");
        System.out.println("Số tiền sau giảm: " + String.format("%.0f", calc.calculateFinalPrice(totalAmount)));

        calc.setDiscountStrategy(new HolidayDiscount());
        System.out.println("\nThêm HolidayDiscount 15% (không sửa code cũ)");
        System.out.println("Số tiền sau giảm: " + String.format("%.0f", calc.calculateFinalPrice(totalAmount)));
    }
}
