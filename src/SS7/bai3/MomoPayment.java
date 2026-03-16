package SS7.bai3;

public class MomoPayment implements PaymentMethod, EWalletPayable {
    @Override
    public void processPayment(double amount) {
        processMomo(amount);
    }

    @Override
    public void processMomo(double amount) {
        System.out.println("Xử lý thanh toán MoMo: " + amount + " - Thành công");
    }
}
