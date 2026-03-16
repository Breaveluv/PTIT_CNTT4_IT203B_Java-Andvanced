package ordermanagement.notification.impl;

import ordermanagement.model.Order;
import ordermanagement.notification.NotificationService;

public class SMSNotification implements NotificationService {
    @Override
    public void sendOrderConfirmation(Order order) {
        System.out.println("\n[SMS] Sending order confirmation");
        System.out.println("To: " + order.getCustomer().getPhone());
        System.out.println("Message: Order #" + order.getOrderId() + " confirmed. Total: VND " + order.getTotal());
        System.out.println("SMS sent successfully\n");
    }

    @Override
    public void sendOrderStatus(Order order, String newStatus) {
        System.out.println("\n[SMS] Order status update");
        System.out.println("To: " + order.getCustomer().getPhone());
        System.out.println("Message: Order #" + order.getOrderId() + " status: " + newStatus);
        System.out.println("SMS sent successfully\n");
    }

    @Override
    public void sendDiscount(Order order, double discountAmount) {
        System.out.println("\n[SMS] Special discount offer");
        System.out.println("To: " + order.getCustomer().getPhone());
        System.out.println("Message: You got VND " + discountAmount + " discount!");
        System.out.println("SMS sent successfully\n");
    }

    @Override
    public String getNotificationTypeName() {
        return "SMS";
    }
}