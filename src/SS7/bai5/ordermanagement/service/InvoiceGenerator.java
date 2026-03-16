package ordermanagement.service;

import ordermanagement.model.Order;
import ordermanagement.model.OrderItem;
import java.time.format.DateTimeFormatter;

public class InvoiceGenerator {
    public String generateInvoice(Order order) {
        StringBuilder invoice = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        invoice.append("\n========================================\n");
        invoice.append("           ORDER INVOICE\n");
        invoice.append("========================================\n");
        invoice.append("Order ID: ").append(order.getOrderId()).append("\n");
        invoice.append("Order Date: ").append(order.getOrderDate().format(formatter)).append("\n");
        invoice.append("Status: ").append(order.getStatus()).append("\n");
        invoice.append("\n--- CUSTOMER INFORMATION ---\n");
        invoice.append("Name: ").append(order.getCustomer().getName()).append("\n");
        invoice.append("Email: ").append(order.getCustomer().getEmail()).append("\n");
        invoice.append("Phone: ").append(order.getCustomer().getPhone()).append("\n");
        invoice.append("Address: ").append(order.getCustomer().getAddress()).append("\n");

        invoice.append("\n--- ORDER ITEMS ---\n");
        invoice.append(String.format("%-30s %10s %15s %15s\n", "Product", "Qty", "Unit Price", "Total"));
        invoice.append("-------------------------------------------------------------\n");

        for (OrderItem item : order.getItems()) {
            invoice.append(String.format("%-30s %10d %15.2f %15.2f\n",
                    item.getProduct().getName().substring(0, Math.min(30, item.getProduct().getName().length())),
                    item.getQuantity(),
                    item.getUnitPrice(),
                    item.getTotal()));
        }

        invoice.append("-------------------------------------------------------------\n");
        invoice.append(String.format("%-30s %35.2f VND\n", "Subtotal", order.getSubtotal()));
        if (order.getDiscountAmount() > 0) {
            invoice.append(String.format("%-30s %35.2f VND\n", "Discount", order.getDiscountAmount()));
        }
        invoice.append(String.format("%-30s %35.2f VND\n", "TOTAL", order.getTotal()));
        invoice.append("========================================\n");

        return invoice.toString();
    }

    public void printInvoice(Order order) {
        System.out.println(generateInvoice(order));
    }
}