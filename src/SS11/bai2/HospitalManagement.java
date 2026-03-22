package SS10.bai2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HospitalManagement {
    public static void main(String[] args) {
        HospitalManagement app = new HospitalManagement();
        // Gọi thử phương thức với một ID bệnh nhân cụ thể (ví dụ: 1)
        app.displayPatientById(1);
    }
    public void displayPatientById(int patientId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Patients WHERE id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, patientId);
                rs = ps.executeQuery();

                if (rs.next()) {
                    System.out.println("Bệnh nhân: " + rs.getString("full_name"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi thực thi truy vấn: " + e.getMessage());
        } finally {
            // Đảm bảo đóng mọi tài nguyên trong khối finally
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println(">>> Hệ thống: Kết nối đã được đóng an toàn.");
                }
            } catch (SQLException ex) {
                System.err.println("Lỗi khi đóng tài nguyên: " + ex.getMessage());
            }
        }
    }
}
