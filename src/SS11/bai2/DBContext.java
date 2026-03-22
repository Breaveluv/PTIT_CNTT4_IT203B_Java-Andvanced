package SS10.bai2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    // Sử dụng hằng số để quản lý cấu hình tập trung
    private static final String USER = "root";
    private static final String PASS = "123456";
    // Gộp cấu hình vào URL và thêm tham số an toàn
    private static final String URL = "jdbc:mysql://localhost:3306/Hospital_DB?useSSL=false&allowPublicKeyRetrieval=true";


    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Lỗi kết nối CSDL: " + e.getMessage());
        }
        return conn;
    }
}