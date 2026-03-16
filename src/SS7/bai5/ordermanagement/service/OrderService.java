package ordermanagement.service;

import ordermanagement.model.Customer;
import ordermanagement.model.Order;
import ordermanagement.model.OrderItem;
import ordermanagement.model.Product;
import ordermanagement.notification.NotificationService;
import ordermanagement.payment.PaymentMethod;
import ordermanagement.repository.OrderRepository;
import ordermanagement.strategy.DiscountStrategy;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private final OrderRepository orderRepository;
    private final List<NotificationService> notificationServices;
    private final InvoiceGenerator invoiceGenerator;

    // Constructor injection for dependency inversion
    public OrderService(OrderRepository orderRepository,
            List<NotificationService> notificationServices,
            InvoiceGenerator invoiceGenerator) {
        this.orderRepository = orderRepository;
        this.notificationServices = notificationServices;
        this.invoiceGenerator = invoiceGenerator;
    }

    public Order createOrder(String orderId, Customer customer) {
        Order order = new Order(orderId, customer);
        System.out.println("Order created: " + orderId);
        return order;
    }

    public void addItemToOrder(Order order, Product product, int quantity) {
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient stock for: " + product.getName());
        }
        OrderItem item = new OrderItem(product, quantity, product.getPrice());
        order.addItem(item);
        product.setQuantity(product.getQuantity() - quantity);
        System.out.println("Item added: " + product.getName() + " x" + quantity);
    }

    public void applyDiscount(Order order, DiscountStrategy discountStrategy) {
        double discountAmount = discountStrategy.calculateDiscount(order);
        order.setDiscountAmount(discountAmount);
        System.out.println("Applied discount: " + discountStrategy.getStrategyName() +
                " - VND " + String.format("%.2f", discountAmount));

        // Notify customers about discount
        for (NotificationService notification : notificationServices) {
            notification.sendDiscount(order, discountAmount);
        }
    }

    public void processPayment(Order order, PaymentMethod paymentMethod) {
        paymentMethod.processPayment(order, order.getTotal());
        order.setStatus("PAID");
        System.out.println("Payment processed successfully via: " + paymentMethod.getPaymentMethodName());
    }

    public void confirmOrder(Order order) {
        if (order.getItems().isEmpty()) {
            throw new IllegalStateException("Cannot confirm empty order");
        }
        order.setStatus("CONFIRMED");
        orderRepository.save(order);

        // Send notifications
        for (NotificationService notification : notificationServices) {
            notification.sendOrderConfirmation(order);
        }

        // Print invoice
        invoiceGenerator.printInvoice(order);
        System.out.println("Order confirmed and saved!");
    }

    public void updateOrderStatus(Order order, String newStatus) {
        order.setStatus(newStatus);
        orderRepository.update(order);

        for (NotificationService notification : notificationServices) {
            notification.sendOrderStatus(order, newStatus);
        }

        System.out.println("Order status updated to: " + newStatus);
    }

    public Optional<Order> getOrderById(String orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void cancelOrder(String orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus("CANCELLED");
            orderRepository.update(order);

            for (NotificationService notification : notificationServices) {
                notification.sendOrderStatus(order, "CANCELLED");
            }

            System.out.println("Order cancelled: " + orderId);
        } else {
            System.out.println("Order not found: " + orderId);
        }
    }

    public void displayOrderDetails(Order order) {
        System.out.println(invoiceGenerator.generateInvoice(order));
    }
}