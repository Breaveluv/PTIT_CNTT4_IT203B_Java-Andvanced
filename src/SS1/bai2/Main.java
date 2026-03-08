package SS1.bai2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Vui lòng nhập năm sinh: ");
            String inputNamSinh = scanner.nextLine();
            int namSinh = Integer.parseInt(inputNamSinh);
            System.out.println("Tuổi của bạn là: " + (2026 - namSinh));

            System.out.println("-------------------------------");

            System.out.print("Nhập tổng số người dùng: ");
            int tongSoNguoi = Integer.parseInt(scanner.nextLine());

            System.out.print("Nhập số lượng nhóm muốn chia: ");
            int soNhom = Integer.parseInt(scanner.nextLine());

            int soNguoiMoiNhom = tongSoNguoi / soNhom; 
            
            System.out.println("Mỗi nhóm sẽ có: " + soNguoiMoiNhom + " người.");

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng chỉ nhập số nguyên!");
        } catch (ArithmeticException e) {
            System.out.println("Lỗi: Không thể chia cho 0! Vui lòng nhập số nhóm lớn hơn 0.");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }

        System.out.println("Hệ thống kết thúc an toàn. Tạm biệt!");
    }
}