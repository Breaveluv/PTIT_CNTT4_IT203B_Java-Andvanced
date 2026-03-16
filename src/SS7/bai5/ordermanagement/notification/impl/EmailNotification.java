package ordermanagement.notification.impl;

import ordermanagement.model.Order;
import ordermanagement.notification.NotificationService;

public class EmailNotification implements NotificationService {
    @Override
    public void sendOrderConfirmation(Order order) {
        System.out.println("\n[EMAIL] Sending order confirmation");
        System.out.println("To: " + order.getCustomer().getEmail());
        System.out.println("Subject: Order Confirmation #" + order.getOrderId());
        System.out.println("Body: Your order has been confirmed. Total: VND " + order.getTotal());
        System.out.println("Email sent successfully\n");
    }

    @Override
    public void sendOrderStatus(Order order, String newStatus) {
        System.out.println("\n[EMAIL] Order status update");
        System.out.println("To: " + order.getCustomer().getEmail());
        System.out.println("Subject: Order #" + order.getOrderId() + " Status: " + newStatus);
        System.out.println("Your order status has been updated to: " + newStatus);
        System.out.println("Email sent successfully\n");
    }

    @Override
    public void sendDiscount(Order order, double discountAmount) {
        System.out.println("\n[EMAIL] Special discount offer");
        System.out.println("To: " + order.getCustomer().getEmail());
        System.out.println("Subject: Exclusive Discount - VND " + discountAmount);
        System.out.println("You've received a special discount of VND " + discountAmount);
        System.out.println("Email sent successfully\n");
    }

    @Override
    public String getNotificationTypeName() {
        return "Email";
    }
}