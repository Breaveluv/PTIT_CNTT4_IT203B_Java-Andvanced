package ordermanagement.payment.impl;

import ordermanagement.model.Order;
import ordermanagement.payment.PaymentMethod;

public class MomoPayment implements PaymentMethod {
    private String phoneNumber;

    public MomoPayment(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean processPayment(Order order, double amount) {
        System.out.println("Processing Momo payment of VND " + amount);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Momo wallet transaction initiated");
        return true;
    }

    @Override
    public String getPaymentMethodName() {
        return "Momo Wallet";
    }

    @Override
    public boolean supportsRefund() {
        return true;
    }
}