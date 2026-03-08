package SS1.bai3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        try {
            System.out.print("Nhập tuổi của người dùng: ");
            String input = scanner.nextLine();
            
            int ageValue = Integer.parseInt(input);

            user.setAge(ageValue);
            
            System.out.println("Cập nhật thành công! Tuổi người dùng: " + user.getAge());

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập vào một số nguyên hợp lệ.");
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi nghiệp vụ: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }
        
        System.out.println("Chương trình kết thúc an toàn.");
    }
}