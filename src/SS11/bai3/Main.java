package SS11.bai3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public void updateBedStatus(Connection conn, String inputId) {
        String sql = "UPDATE Beds SET bed_status = 'Occupied' WHERE bed_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, inputId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Đã cập nhật giường bệnh " + inputId + " thành công!");
            } else {
                System.err.println("Lỗi: Mã giường '" + inputId + "' không tồn tại trong hệ thống!");
            }

        } catch (SQLException e) {
            System.err.println("Lỗi kết nối hoặc thực thi SQL: " + e.getMessage());
        }
    }
}