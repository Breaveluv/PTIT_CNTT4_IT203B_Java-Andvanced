package MiniPRJ2;

import java.util.Scanner;

public class Main {
    public  static void main(String[] args) {
        ProductDatabase db = ProductDatabase.getInstance();
        Scanner sc = new Scanner(System.in);
        int choice;


        do {
            System.out.println("\n------------QUẢN LÍ SẢN PHẨM---------------\n");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Xem danh sách sản phẩm");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Thoát");
            System.out.println("Lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Chọn loại (1: Vật lý, 2: Digital): ");
                    int type = Integer.parseInt(sc.nextLine());
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Tên: ");
                    String name = sc.nextLine();
                    System.out.print("Giá: ");
                    double price = Double.parseDouble(sc.nextLine());
                    System.out.print(type == 1 ? "Cân nặng: " : "Dung lượng (MB): ");
                    double extra = Double.parseDouble(sc.nextLine());

                    db.addProduct(ProductFactory.createProduct(type, id, name, price, extra));
                    System.out.println("Thêm thành công!");
                    break;
                case 2:
                    db.getAllProducts().forEach(Product::displayInfo);
                    break;
                case 3:
                    System.out.println("Nhập Id cần cập nhật");
                    String updateID = sc.nextLine();
                    Product p = db.findById(updateID);
                    if (p != null) {
                        System.out.println("Tên mới: ");
                        String newName = sc.nextLine();
                        System.out.println("Giá mới: ");
                        double newPrice = Double.parseDouble(sc.nextLine());
                        p.setName(newName);
                        p.setPrice(newPrice);
                        System.out.println("Cập nhật thành công!");

                    } else {
                        System.out.println("Không tìm thấy");

                    }
                    break;

                case 4:
                    System.out.println("Nhập Id cần xóa");
                    String deleteID = sc.nextLine();
                    if (db.removeProduct(deleteID)) {
                        System.out.println("Xóa thành công!");
                    } else {
                        System.out.println("Không tìm thấy");
                    }
                    break;


            }


        } while (choice != 5);
    }
}
