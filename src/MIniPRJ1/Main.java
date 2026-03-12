package MIniPRJ1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n========= PRODUCT MANAGEMENT SYSTEM =========");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Cập nhật số lượng theo ID");
            System.out.println("4. Xóa sản phẩm đã hết hàng");
            System.out.println("5. Thoát chương trình");
            System.out.print("Lựa chọn của bạn: ");

            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Nhập ID: "); int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nhập tên: "); String name = scanner.nextLine();
                        System.out.print("Nhập giá: "); double price = scanner.nextDouble();
                        System.out.print("Nhập số lượng: "); int quantity = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nhập danh mục: "); String category = scanner.nextLine();

                        productManager.addProduct(new Product(id, name, price, quantity, category));
                        System.out.println("Thêm thành công!");
                        break;
                    case 2:
                        productManager.displayAllProducts();
                        break;
                    case 3:
                        System.out.print("Nhập ID cần cập nhật: "); int uId = scanner.nextInt();
                        System.out.print("Nhập số lượng mới: "); int uQty = scanner.nextInt();
                        productManager.updateQuantity(uId, uQty);
                        System.out.println("Cập nhật thành công!");
                        break;
                    case 4:
                        productManager.deleteOutOfStock();
                        System.out.println("Đã dọn dẹp các sản phẩm hết hàng.");
                        break;
                    case 5:
                        System.out.println("Tạm biệt!");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (InvalidProductException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println("Lỗi nhập liệu, vui lòng thử lại!");
                scanner.nextLine(); // Clear bộ nhớ đệm
            }
        } while (choice != 5);
    }
}