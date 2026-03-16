package SS7.bai3;

public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        System.out.println("--- Thanh toán các loại khác nhau ---");
        CODPayment codPayment = new CODPayment();
        processor.process(codPayment, 500000);

        CreditCardPayment creditCardPayment = new CreditCardPayment();
        processor.process(creditCardPayment, 1000000);

        MomoPayment momoPayment = new MomoPayment();
        processor.process(momoPayment, 750000);

        System.out.println("\n--- Kiểm tra LSP (Liskov Substitution Principle) ---");
        PaymentMethod lspPaymentMethod = new CreditCardPayment();
        System.out.println("Sử dụng CreditCardPayment qua PaymentMethod interface:");
        processor.process(lspPaymentMethod, 2000000);

        lspPaymentMethod = new MomoPayment();
        System.out.println("Thay thế bằng MomoPayment qua PaymentMethod interface:");
        processor.process(lspPaymentMethod, 1500000);

        System.out.println("\nChương trình vẫn chạy đúng khi thay thế các implementation cho nhau trong cùng một interface.");
    }
}
