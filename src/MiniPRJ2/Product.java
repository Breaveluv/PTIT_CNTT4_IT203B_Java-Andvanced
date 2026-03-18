package MiniPRJ2;
public abstract class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters và Setters
    public String getId() { return id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public abstract void displayInfo();
}

class PhysicalProduct extends Product {
    private double weight;

    public PhysicalProduct(String id, String name, double price, double weight) {
        super(id, name, price);
        this.weight = weight;
    }

    @Override
    public void displayInfo() {
        System.out.printf("[Physical] ID: %s | Name: %s | Price: %.2f | Weight: %.2f kg\n",
                getId(), getName(), getPrice(), weight);
    }
}

class DigitalProduct extends Product {
    private double size;

    public DigitalProduct(String id, String name, double price, double size) {
        super(id, name, price);
        this.size = size;
    }

    @Override
    public void displayInfo() {
        System.out.printf("[Digital] ID: %s | Name: %s | Price: %.2f | Size: %.2f MB\n",
                getId(), getName(), getPrice(), size);
    }
}
