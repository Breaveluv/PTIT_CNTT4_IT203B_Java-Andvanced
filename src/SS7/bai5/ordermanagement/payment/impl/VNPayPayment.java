package ordermanagement.payment.impl;

import ordermanagement.model.Order;
import ordermanagement.payment.PaymentMethod;

public class VNPayPayment implements PaymentMethod {
    private String bankCode;

    public VNPayPayment(String bankCode) {
        this.bankCode = bankCode;
    }

    @Override
    public boolean processPayment(Order order, double amount) {
        System.out.println("Processing VNPay payment of VND " + amount);
        System.out.println("Bank Code: " + bankCode);
        System.out.println("Redirecting to VNPay gateway...");
        System.out.println("Payment authorized successfully");
        return true;
    }

    @Override
    public String getPaymentMethodName() {
        return "VNPay";
    }

    @Override
    public boolean supportsRefund() {
        return true;
    }
}