package SS7.bai3;

public class CODPayment implements PaymentMethod, CODPayable {
    @Override
    public void processPayment(double amount) {
        processCOD(amount);
    }

    @Override
    public void processCOD(double amount) {
        System.out.println("Xử lý thanh toán COD: " + amount + " - Thành công");
    }
}
