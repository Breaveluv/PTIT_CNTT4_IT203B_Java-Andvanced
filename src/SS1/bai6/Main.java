package SS1.bai6;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        try {
            System.out.print("Nhập tên người dùng: ");
            String nameInput = scanner.nextLine();
            if (!nameInput.trim().isEmpty()) {
                user.setName(nameInput);
            }

            System.out.print("Nhập năm sinh: ");
            int namSinh = Integer.parseInt(scanner.nextLine());
            user.setAge(2026 - namSinh);

            System.out.print("Nhập số nhóm muốn chia: ");
            int soNhom = Integer.parseInt(scanner.nextLine());
            int moiNhom = 100 / soNhom; 
            user.displayNameUpperCase();
            System.out.println("Mỗi nhóm có: " + moiNhom + " người.");

        } catch (NumberFormatException e) {
            logError("Định dạng số không hợp lệ - " + e.getMessage());
        } catch (ArithmeticException e) {
            logError("Lỗi toán học (Chia cho 0) - " + e.getMessage());
        } catch (InvalidAgeException e) {
            logError("Vi phạm quy tắc nghiệp vụ - " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            System.out.println("[INFO] " + LocalDateTime.now() + " : Giải phóng tài nguyên hoàn tất.");
        }
    }

   
    private static void logError(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.err.println("[ERROR] " + timestamp + " : " + message);
    }
}