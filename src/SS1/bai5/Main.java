package SS1.bai5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        try {
            System.out.print("Nhập tuổi đăng ký thành viên: ");
            int inputAge = Integer.parseInt(scanner.nextLine());

            user.setAge(inputAge);
            
            System.out.println("Chúc mừng! Đăng ký thành công với số tuổi: " + user.getAge());

        } catch (NumberFormatException e) {
            System.out.println("Thông báo: Vui lòng nhập số nguyên hợp lệ.");
        } catch (InvalidAgeException e) {
            System.err.println("NGHIỆP VỤ TỪ CHỐI: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Hệ thống: Hoàn tất kiểm tra dữ liệu.");
        }
    }
}