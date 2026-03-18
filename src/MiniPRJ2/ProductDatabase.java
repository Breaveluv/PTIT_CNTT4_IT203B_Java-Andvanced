package MiniPRJ2;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabase {
    private static ProductDatabase instance;
    private List<Product> products;

    private ProductDatabase() {
        products = new ArrayList<>();
    }

    public static ProductDatabase getInstance() {
        if (instance == null) {
            instance = new ProductDatabase();
        }
        return instance;
    }


    public void addProduct(Product p) {
        products.add(p);
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public boolean removeProduct(String id) {
        return products.removeIf(p -> p.getId().equalsIgnoreCase(id));
    }

    public Product findById(String id) {
        return products.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }
}