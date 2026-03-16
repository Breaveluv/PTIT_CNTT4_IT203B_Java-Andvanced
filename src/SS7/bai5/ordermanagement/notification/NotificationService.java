package ordermanagement.notification;

import ordermanagement.model.Order;

public interface NotificationService {
    void sendOrderConfirmation(Order order);

    void sendOrderStatus(Order order, String newStatus);

    void sendDiscount(Order order, double discountAmount);

    String getNotificationTypeName();
}