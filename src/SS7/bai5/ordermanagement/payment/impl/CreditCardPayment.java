package ordermanagement.payment.impl;

import ordermanagement.model.Order;
import ordermanagement.payment.PaymentMethod;

public class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolder;
    private String expiryDate;

    public CreditCardPayment(String cardNumber, String cardHolder, String expiryDate) {
        this.cardNumber = maskCardNumber(cardNumber);
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean processPayment(Order order, double amount) {
        System.out.println("Processing Credit Card payment of VND " + amount);
        System.out.println("Card Holder: " + cardHolder);
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Payment authorized successfully");
        return true;
    }

    @Override
    public String getPaymentMethodName() {
        return "Credit Card";
    }

    @Override
    public boolean supportsRefund() {
        return true;
    }

    private String maskCardNumber(String cardNumber) {
        return "**** **** **** " + cardNumber.substring(Math.max(0, cardNumber.length() - 4));
    }
}