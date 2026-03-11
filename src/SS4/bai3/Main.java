package SS4.bai3;

public class Main {
    public String processedEmail = "";
    public String errorMessage = "";

    public void processEmail(String email) {
        // Reset trạng thái trước khi xử lý
        processedEmail = "";
        errorMessage = "";

        if (email == null || !email.contains("@") || email.endsWith("@")) {
            errorMessage = "INVALID_FORMAT";
        } else {
            // Chuẩn hóa về chữ thường và lưu vào biến thành viên
            processedEmail = email.toLowerCase();
            errorMessage = "NONE";
        }
    }
}