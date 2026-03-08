package SS1.bai4;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hệ thống: Bắt đầu quá trình lưu dữ liệu...");
        
        try {
            processUserData();
            System.out.println("Hệ thống: Lưu dữ liệu hoàn tất!"); 
        } catch (IOException e) {
            System.err.println("Method A (main) thông báo: Đã bắt được lỗi lưu file! Chi tiết: " + e.getMessage());
        } finally {
            System.out.println("Hệ thống: Kết thúc tiến trình.");
        }
    }

    public static void processUserData() throws IOException {
        System.out.println("Method B: Đang xử lý dữ liệu người dùng và gọi hàm lưu...");
        // Gọi Method C
        saveToFile();
    }

    public static void saveToFile() throws IOException {
        System.out.println("Method C: Đang cố gắng ghi thông tin vào ổ cứng...");
        
        boolean errorOccurred = true; 
        
        if (errorOccurred) {
            throw new IOException("Lỗi kết nối ổ cứng hoặc không tìm thấy file.");
        }
    }
}