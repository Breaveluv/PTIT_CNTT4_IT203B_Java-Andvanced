package ordermanagement.payment.impl;

import ordermanagement.model.Order;
import ordermanagement.payment.PaymentMethod;

public class CODPayment implements PaymentMethod {
    @Override
    public boolean processPayment(Order order, double amount) {
        System.out.println("Processing COD (Cash on Delivery) payment of VND " + amount);
        System.out.println("Customer will pay upon delivery");
        return true;
    }

    @Override
    public String getPaymentMethodName() {
        return "Cash on Delivery (COD)";
    }

    @Override
    public boolean supportsRefund() {
        return true;
    }
}