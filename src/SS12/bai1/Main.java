package SS12.bai1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public void login(Connection conn, String code, String pass) {
        // 1. Sử dụng dấu hỏi chấm (?) làm placeholder để tránh cộng chuỗi
        String sql = "SELECT * FROM Doctors WHERE code = ? AND pass = ?";

        // 2. Sử dụng Try-with-resources để tự động đóng tài nguyên
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 3. Gán giá trị vào các tham số.
            // JDBC sẽ tự động xử lý các ký tự nguy hiểm.
            pstmt.setString(1, code);
            pstmt.setString(2, pass);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Đăng nhập thành công! Chào bác sĩ: " + rs.getString("code"));
                } else {
                    System.out.println("Sai mã số hoặc mật khẩu.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi kết nối cơ sở dữ liệu.");
        }
    }
}