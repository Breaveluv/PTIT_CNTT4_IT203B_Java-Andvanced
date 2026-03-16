package SS7.bai4;

public class Main {
    public static void main(String[] args) {
        // Dùng FileOrderRepository và EmailService
        OrderRepository fileOrderRepository = new FileOrderRepository();
        NotificationService emailService = new EmailService();
        OrderService orderService1 = new OrderService(fileOrderRepository, emailService);
        orderService1.createOrder(new Order("ORD001"));

        System.out.println("\n---------------------------------\n");

        // Đổi sang DatabaseOrderRepository và SMSNotification
        OrderRepository databaseOrderRepository = new DatabaseOrderRepository();
        NotificationService smsNotification = new SMSNotification();
        OrderService orderService2 = new OrderService(databaseOrderRepository, smsNotification);
        orderService2.createOrder(new Order("ORD002"));
    }
}
