import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        // 1. Cấu hình thông tin kết nối
        // Lưu ý: Thay "test_db" bằng tên database bạn đã tạo trong MySQL
        String url = "jdbc:mysql://localhost:3306/test_db";

        // Thông thường dùng user 'root'. Nếu máy bạn đặt pass thì điền vào, không thì để ""
        String user = "root";
        String password = "123456";
//
        try {
//            // 2. Thiết lập kết nối
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
//            System.out.println("Kết nối cơ sở dữ liệu thành công!");
//
//            // 3. Xóa bảng 'device' nếu đã tồn tại để làm mới
//            String drop = "DROP TABLE IF EXISTS device";
//            stmt.executeUpdate(drop);
//            System.out.println("Đã xóa bảng cũ (nếu có).");
//
//            // 4. Câu lệnh tạo bảng chuẩn SQL
//            String createTable = """
//                    CREATE TABLE device (
//                        id INT AUTO_INCREMENT PRIMARY KEY,
//                        name VARCHAR(255)
//                    )
//                    """;
//
//            // Thực thi lệnh tạo bảng
//            stmt.execute(createTable);
//            System.out.println("Tạo bảng 'device' thành công!");
//
//            // 5. Thử chèn một dòng dữ liệu để kiểm tra
//            String insert = "INSERT INTO device (name) VALUES ('Laptop Acer Nitro 5')";
//            stmt.executeUpdate(insert);

                String select = "select * from device";
            ResultSet rs = stmt.executeQuery(select);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(id + " " + name);
            }
            // Đóng kết nối


        } catch (Exception e) {
            // In chi tiết lỗi ra console để dễ xử lý
            System.out.println("Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}