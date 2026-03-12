package MIniPRJ1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductManager {
    public List<Product> productList = new ArrayList<>();

    public void addProduct(Product product) {
        boolean exists = productList.stream().anyMatch(p -> p.getId() == product.getId());
        if (exists) {
            throw new InvalidProductException("Lỗi: ID " + product.getId() + " đã tồn tại!");
        }
        productList.add(product);
    }

    public void displayAllProducts() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("| ID    | Tên Sản Phẩm    | Giá        | Số Lượng   | Danh Mục        |");
        System.out.println("---------------------------------------------------------------------------");
        productList.forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------------");
    }

    public void updateQuantity(int id, int newQuantity) {
        Optional<Product> productOpt = productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst();

        productOpt.ifPresentOrElse(
                p -> p.setQuantity(newQuantity),
                () -> { throw new InvalidProductException("Lỗi: Không tìm thấy sản phẩm ID " + id); }
        );
    }

    public void deleteOutOfStock() {
        productList.removeIf(p -> p.getQuantity() == 0);
    }
}