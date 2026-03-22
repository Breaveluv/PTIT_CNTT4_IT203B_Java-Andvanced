package SS10.bai1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HospitalManagement {

    public void getPatientInfo(int patientId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Khởi tạo kết nối từ lớp cấu hình tập trung
            conn = DBContext.getConnection();

            if (conn != null) {
                String sql = "SELECT * FROM Patients WHERE id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, patientId);

                rs = ps.executeQuery();

                if (rs.next()) {
                    String name = rs.getString("full_name");
                    String diagnosis = rs.getString("diagnosis");
                    System.out.println("Kết quả: Bệnh nhân " + name + " | Chẩn đoán: " + diagnosis);
                } else {
                    System.out.println("Thông báo: Không tìm thấy bệnh nhân có ID " + patientId);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi hệ thống khi truy xuất dữ liệu: " + e.getMessage());
        } finally {
            // Đảm bảo mọi phương thức đều phải đóng kết nối trong khối finally
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println(">>> Hệ thống: Đã đóng kết nối an toàn.");
                }
            } catch (SQLException ex) {
                System.err.println("Lỗi khi giải phóng tài nguyên: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        HospitalManagement app = new HospitalManagement();
        // Giả sử lấy thông tin bệnh nhân có ID 101
        app.getPatientInfo(101);
    }
}
