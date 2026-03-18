package MiniPRJ2;

public class ProductFactory {
    public static Product createProduct(int type, String id, String name, double price, double extraValue) {
        switch (type) {
            case 1:
                return new PhysicalProduct(id, name, price, extraValue);
            case 2:
                return new DigitalProduct(id, name, price, extraValue);
            default:
                throw new IllegalArgumentException("Loại sản phẩm không hợp lệ!");
        }
    }
}
