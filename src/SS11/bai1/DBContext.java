package SS11.bai1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    // Sử dụng hằng số cho cấu hình để quản lý tập trung
    private static final String USER = "root";
    private static final String PASS = "123456"; // Thay bằng mật khẩu máy bạn
    private static final String URL = "jdbc:mysql://localhost:3306/Hospital_DB?useSSL=false&allowPublicKeyRetrieval=true";

    public static Connection getConnection() throws SQLException {
        try {
            // Đăng ký Driver cho MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Lỗi: Không tìm thấy Driver MySQL!");
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}