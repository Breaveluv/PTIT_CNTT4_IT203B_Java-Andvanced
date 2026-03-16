package SS7.bai3;

public class CreditCardPayment implements PaymentMethod, CardPayable {
    @Override
    public void processPayment(double amount) {
        processCreditCard(amount);
    }

    @Override
    public void processCreditCard(double amount) {
        System.out.println("Xử lý thanh toán thẻ tín dụng: " + amount + " - Thành công");
    }
}
