package SS7.bai5;

import ordermanagement.model.Customer;
import ordermanagement.model.Order;
import ordermanagement.model.Product;
import ordermanagement.notification.NotificationService;
import ordermanagement.notification.impl.EmailNotification;
import ordermanagement.notification.impl.SMSNotification;
import ordermanagement.payment.PaymentMethod;
import ordermanagement.payment.impl.CODPayment;
import ordermanagement.payment.impl.CreditCardPayment;
import ordermanagement.payment.impl.MomoPayment;
import ordermanagement.payment.impl.VNPayPayment;
import ordermanagement.repository.OrderRepository;
import ordermanagement.repository.impl.DatabaseOrderRepository;
import ordermanagement.repository.impl.FileOrderRepository;
import ordermanagement.service.InvoiceGenerator;
import ordermanagement.service.OrderService;
import ordermanagement.strategy.DiscountStrategy;
import ordermanagement.strategy.impl.FixedDiscount;
import ordermanagement.strategy.impl.HolidayDiscount;
import ordermanagement.strategy.impl.PercentageDiscount;

import java.util.*;

public class Main {
    private static OrderService orderService;
    private static Map<String, Product> products = new HashMap<>();
    private static Map<String, Customer> customers = new HashMap<>();
    private static Order currentOrder;
    private static Scanner scanner = new Scanner(System.in);
    private static int orderCounter = 1000;

    public static void main(String[] args) {
        initializeSystem();
        displayMainMenu();
    }

    private static void initializeSystem() {
        OrderRepository orderRepository = new DatabaseOrderRepository();

        List<NotificationService> notifications = Arrays.asList(
                new EmailNotification(),
                new SMSNotification());

        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();

        orderService = new OrderService(orderRepository, notifications, invoiceGenerator);

        // Initialize sample products
        initializeProducts();

        // Initialize sample customers
        initializeCustomers();

        System.out.println("==================================================");
        System.out.println("     SOLID Order Management System                 ");
        System.out.println("     Single Responsibility & Open/Closed Principles ");
        System.out.println("     Liskov Substitution & Interface Segregation   ");
        System.out.println("     Dependency Inversion                          ");
        System.out.println("==================================================\n");
    }

    private static void initializeProducts() {
        products.put("SP01", new Product("SP01", "Laptop", 15000000, "Electronics", 10));
        products.put("SP02", new Product("SP02", "Smartphone", 8000000, "Electronics", 20));
        products.put("SP03", new Product("SP03", "T-Shirt", 200000, "Fashion", 50));
        products.put("SP04", new Product("SP04", "Jeans", 500000, "Fashion", 30));
        products.put("SP05", new Product("SP05", "Book", 100000, "Education", 100));
    }

    private static void initializeCustomers() {
        customers.put("KH01",
                new Customer("KH01", "Nguyen Van A", "nguyenvana@email.com", "0901234567", "123 ABC Street, Hanoi"));
        customers.put("KH02",
                new Customer("KH02", "Tran Thi B", "tranthib@email.com", "0912345678", "456 XYZ Street, Ho Chi Minh"));
        customers.put("KH03",
                new Customer("KH03", "Le Van C", "levanc@email.com", "0923456789", "789 DEF Street, Da Nang"));
    }

    private static void displayMainMenu() {
        while (true) {
            System.out.println("\n==================================================");
            System.out.println("              MAIN MENU                           ");
            System.out.println("==================================================");
            System.out.println("  1. Add new product                              ");
            System.out.println("  2. Add new customer                             ");
            System.out.println("  3. Create new order                             ");
            System.out.println("  4. View order list                              ");
            System.out.println("  5. Calculate total revenue                      ");
            System.out.println("  6. Add new payment method                       ");
            System.out.println("  7. Add new discount strategy                    ");
            System.out.println("  8. Exit                                         ");
            System.out.println("==================================================");
            System.out.print("Choose an option: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    addNewProduct();
                    break;
                case 2:
                    addNewCustomer();
                    break;
                case 3:
                    createNewOrder();
                    break;
                case 4:
                    viewOrderHistory();
                    break;
                case 5:
                    calculateTotalRevenue();
                    break;
                case 6:
                    addNewPaymentMethod();
                    break;
                case 7:
                    addNewDiscountStrategy();
                    break;
                case 8:
                    System.out.println("Thank you for using the Order Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNewProduct() {
        System.out.println("\n--- ADD NEW PRODUCT ---");
        System.out.print("Product ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Product Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Price: ");
        double price = getDoubleInput();
        System.out.print("Category: ");
        String category = scanner.nextLine().trim();
        System.out.print("Quantity: ");
        int quantity = getIntInput();

        Product product = new Product(id, name, price, category, quantity);
        products.put(id, product);
        System.out.println("✓ Product added: " + id);
    }

    private static void addNewCustomer() {
        System.out.println("\n--- ADD NEW CUSTOMER ---");
        System.out.print("Customer ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Customer Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Phone: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Address: ");
        String address = scanner.nextLine().trim();

        Customer customer = new Customer(id, name, email, phone, address);
        customers.put(id, customer);
        System.out.println("✓ Customer added: " + id);
    }

    private static void createNewOrder() {
        if (currentOrder != null && !currentOrder.getStatus().equals("CANCELLED")) {
            System.out.println("You have an order in progress. Please confirm or cancel it first.");
            return;
        }

        System.out.println("\n--- CREATE NEW ORDER ---");
        System.out.print("Customer ID (KH01, KH02, KH03): ");
        String customerId = scanner.nextLine().trim();

        if (!customers.containsKey(customerId)) {
            System.out.println("Customer does not exist!");
            return;
        }

        String orderId = "ORD" + (++orderCounter);
        Customer customer = customers.get(customerId);
        currentOrder = orderService.createOrder(orderId, customer);
        System.out.println("✓ Order created: " + orderId);
        System.out.println("Customer: " + customer.getName());

        // Add products
        addItemsToOrder();

        // Apply discount
        applyDiscountToOrder();

        // Process payment
        processPaymentForOrder();

        // Confirm order
        confirmCurrentOrder();
    }

    private static void addItemsToOrder() {
        while (true) {
            System.out.println("\n--- ADD PRODUCTS TO ORDER ---");
            viewProducts();
            System.out.print("Product ID (or 'done' to finish): ");
            String productId = scanner.nextLine().trim();

            if (productId.equalsIgnoreCase("done")) {
                break;
            }

            if (!products.containsKey(productId)) {
                System.out.println("Product does not exist!");
                continue;
            }

            System.out.print("Quantity: ");
            int quantity = getIntInput();

            try {
                Product product = products.get(productId);
                orderService.addItemToOrder(currentOrder, product, quantity);
                System.out.println("✓ Product added!");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void applyDiscountToOrder() {
        if (currentOrder.getItems().isEmpty()) {
            return;
        }

        System.out.println("\n--- APPLY DISCOUNT ---");
        System.out.println("1. Percentage discount");
        System.out.println("2. Fixed discount");
        System.out.println("3. Holiday discount");
        System.out.println("4. No discount");
        System.out.print("Choose discount type: ");

        int choice = getIntInput();
        DiscountStrategy strategy = null;

        switch (choice) {
            case 1:
                System.out.print("Enter discount percentage (0-100): ");
                double percentage = getDoubleInput();
                strategy = new PercentageDiscount(percentage);
                break;
            case 2:
                System.out.print("Enter discount amount (VND): ");
                double amount = getDoubleInput();
                strategy = new FixedDiscount(amount);
                break;
            case 3:
                System.out.print("Enter holiday discount percentage (0-100): ");
                double holidayPercent = getDoubleInput();
                strategy = new HolidayDiscount(holidayPercent);
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        if (strategy != null) {
            orderService.applyDiscount(currentOrder, strategy);
        }
    }

    private static void processPaymentForOrder() {
        if (currentOrder.getItems().isEmpty()) {
            return;
        }

        System.out.println("\n--- PAYMENT ---");
        System.out.println("Amount to pay: VND " + String.format("%.2f", currentOrder.getTotal()));
        System.out.println("1. Cash on Delivery (COD)");
        System.out.println("2. Credit Card");
        System.out.println("3. Momo Wallet");
        System.out.println("4. VNPay");
        System.out.print("Choose payment method: ");

        int choice = getIntInput();
        PaymentMethod paymentMethod = null;

        switch (choice) {
            case 1:
                paymentMethod = new CODPayment();
                break;
            case 2:
                System.out.print("Enter last 4 digits of card: ");
                String cardNum = scanner.nextLine().trim();
                System.out.print("Card holder name: ");
                String cardHolder = scanner.nextLine().trim();
                System.out.print("Expiry date (MM/YY): ");
                String expiry = scanner.nextLine().trim();
                paymentMethod = new CreditCardPayment(cardNum, cardHolder, expiry);
                break;
            case 3:
                System.out.print("Phone number: ");
                String phone = scanner.nextLine().trim();
                paymentMethod = new MomoPayment(phone);
                break;
            case 4:
                System.out.print("Bank code (VCB, TCB, etc.): ");
                String bankCode = scanner.nextLine().trim();
                paymentMethod = new VNPayPayment(bankCode);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        if (paymentMethod != null) {
            orderService.processPayment(currentOrder, paymentMethod);
        }
    }

    private static void confirmCurrentOrder() {
        if (currentOrder == null) {
            System.out.println("No order to confirm.");
            return;
        }

        if (currentOrder.getItems().isEmpty()) {
            System.out.println("Cannot confirm empty order.");
            return;
        }

        try {
            orderService.confirmOrder(currentOrder);
            currentOrder = null;
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewOrderHistory() {
        List<Order> allOrders = orderService.getAllOrders();

        if (allOrders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        System.out.println("\n==================================================");
        System.out.println("                   ORDER HISTORY                   ");
        System.out.println("==================================================");
        System.out.printf("%-12s %-20s %-12s %-12s\n", "Order ID", "Customer", "Status", "Total");
        System.out.println("--------------------------------------------------");

        for (Order order : allOrders) {
            System.out.printf("%-12s %-20s %-12s %-12.2f VND\n",
                    order.getOrderId(),
                    order.getCustomer().getName().substring(0, Math.min(20, order.getCustomer().getName().length())),
                    order.getStatus(),
                    order.getTotal());
        }

        System.out.println("==================================================");

        System.out.print("\nView invoice details for order ID (or Enter to skip): ");
        String orderId = scanner.nextLine().trim();

        if (!orderId.isEmpty()) {
            orderService.getOrderById(orderId).ifPresentOrElse(
                    orderService::displayOrderDetails,
                    () -> System.out.println("Order does not exist."));
        }
    }

    private static void calculateTotalRevenue() {
        List<Order> allOrders = orderService.getAllOrders();
        double totalRevenue = allOrders.stream()
                .filter(order -> "CONFIRMED".equals(order.getStatus()) || "PAID".equals(order.getStatus()))
                .mapToDouble(Order::getTotal)
                .sum();

        System.out.println("\n--- TOTAL REVENUE ---");
        System.out.println("Total revenue: " + String.format("%.2f", totalRevenue) + " VND");
        System.out.println("Number of orders: " + allOrders.size());
    }

    private static void addNewPaymentMethod() {
        System.out.println("\n--- ADD NEW PAYMENT METHOD ---");
        System.out.print("New payment method name: ");
        String paymentName = scanner.nextLine().trim();
        System.out.println("✓ Added payment method " + paymentName);
        System.out.println("(Simulating system extension - in practice, need to implement new class)");
    }

    private static void addNewDiscountStrategy() {
        System.out.println("\n--- ADD NEW DISCOUNT STRATEGY ---");
        System.out.print("New discount strategy description: ");
        String discountDescription = scanner.nextLine().trim();
        System.out.println("✓ Added discount strategy: " + discountDescription);
        System.out.println("(Simulating system extension - in practice, need to implement new class)");
    }

    private static void viewProducts() {
        System.out.println("\n==================================================");
        System.out.println("                    PRODUCT LIST                   ");
        System.out.println("==================================================");
        System.out.printf("%-8s %-20s %-12s %-15s %-8s\n", "ID", "Name", "Price", "Category", "Qty");
        System.out.println("--------------------------------------------------");
        for (Product product : products.values()) {
            System.out.printf("%-8s %-20s %-12.0f %-15s %-8d\n",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getCategory(),
                    product.getQuantity());
        }
        System.out.println("==================================================");
    }

    private static int getIntInput() {
        try {
            int value = Integer.parseInt(scanner.nextLine().trim());
            return value;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
            return -1;
        }
    }

    private static double getDoubleInput() {
        try {
            double value = Double.parseDouble(scanner.nextLine().trim());
            return value;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
            return -1;
        }
    }
}
