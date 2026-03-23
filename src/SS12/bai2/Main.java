package SS12.bai2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public void updateVitals(Connection conn) {
        int patientId = 101;
        double temp = 37.5;
        int heartRate = 85;

        // Chuỗi SQL sử dụng dấu hỏi chấm (?) để tách biệt phần lệnh và phần dữ liệu
        String sql = "UPDATE Vitals SET temperature = ?, heart_rate = ? WHERE p_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, temp);
            pstmt.setInt(2, heartRate);
            pstmt.setInt(3, patientId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cập nhật thành công!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}