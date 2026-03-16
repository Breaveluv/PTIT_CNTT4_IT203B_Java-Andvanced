package SS7.bai6;

public class WebsiteDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double amount, String discountCode) {
        if (discountCode.equals("WEB10")) {
            System.out.println("Áp dụng giảm giá 10% cho đơn hàng website");
            return amount * 0.9;
        }
        return amount;
    }
}
