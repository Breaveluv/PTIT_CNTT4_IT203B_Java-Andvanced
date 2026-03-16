package SS7.bai1;

import java.util.ArrayList;
import java.util.List;

class Order {
    String orderId;
    Customer customer;
    List<Product> products = new ArrayList<Product>();
    double totalAmount;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}
