package SS7.bai4;

public class OrderService {
    private final OrderRepository orderRepository;
    private final NotificationService notificationService;

    public OrderService(OrderRepository orderRepository, NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
    }

    public void createOrder(Order order) {
        orderRepository.save(order);
        notificationService.send("Đơn hàng " + order.getOrderId() + " đã được tạo", "customer");
    }
}
