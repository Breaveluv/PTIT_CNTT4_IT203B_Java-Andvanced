package ordermanagement.payment;

import ordermanagement.model.Order;

public interface PaymentMethod {
    boolean processPayment(Order order, double amount);

    String getPaymentMethodName();

    boolean supportsRefund();
}