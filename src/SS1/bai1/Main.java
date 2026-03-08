package SS1.bai1;

import java.util.Scanner;
import java.time.Year;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Vui lòng nhập năm sinh của bạn: ");
            String input = scanner.nextLine();

            int namSinh = Integer.parseInt(input);

            int namHienTai = Year.now().getValue();
            int tuoi = namHienTai - namSinh;

            if (tuoi < 0) {
                System.out.println("Năm sinh không hợp lệ (lớn hơn năm hiện tại).");
            } else {
                System.out.println("Đăng ký thành công! Tuổi của bạn là: " + tuoi);
            }

        } catch (NumberFormatException e) {
           
            System.out.println("Lỗi: Định dạng nhập vào không hợp lệ. Vui lòng nhập số nguyên thay vì chữ hoặc ký tự đặc biệt.");
        } finally {
           
            if (scanner != null) {
                scanner.close();
            }
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }
    }
}