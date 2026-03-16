package SS7.bai6;

public class WebsitePaymentMethod implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Xử lý thanh toán thẻ tín dụng qua cổng thanh toán online: " + amount);
    }
}
