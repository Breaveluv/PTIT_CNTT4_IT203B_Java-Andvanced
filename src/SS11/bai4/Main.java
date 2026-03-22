package SS10.bai4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    // Hàm lọc các ký tự nguy hiểm
    public String sanitizeInput(String input) {
        if (input == null) return "";

        // 1. Loại bỏ các ký tự comment trong SQL như --
        // 2. Loại bỏ dấu chấm phẩy ; để tránh chạy nhiều lệnh cùng lúc
        // 3. Thay thế dấu nháy đơn ' bằng cách thêm dấu thoát hoặc xóa bỏ
        // Ở đây chúng ta sẽ xóa bỏ các ký tự gây hại để đảm bảo an toàn nhất
        return input.replace("'", "")
                .replace("--", "")
                .replace(";", "");
    }

    public void searchPatient(Connection conn, String patientName) {
        try {
            // Bước 1: Làm sạch dữ liệu đầu vào
            String safeName = sanitizeInput(patientName);

            // Bước 2: Thực thi với Statement
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Patients WHERE full_name = '" + safeName + "'";

            System.out.println("Executing query: " + sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Bệnh nhân: " + rs.getString("full_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}