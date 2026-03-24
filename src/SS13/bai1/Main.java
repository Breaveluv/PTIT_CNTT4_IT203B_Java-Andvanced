package SS13.bai1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/Rikkei_Hospital";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public void capPhatThuoc(int medicineId, int patientId) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);

            conn.setAutoCommit(false);

            String sqlUpdate = "UPDATE Medicine_Inventory SET quantity = quantity - 1 WHERE medicine_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sqlUpdate);
            ps1.setInt(1, medicineId);
            ps1.executeUpdate();

            String sqlInsert = "INSERT INTO Prescription_History (patient_id, medicine_id) VALUES (?, ?)";
            PreparedStatement ps2 = conn.prepareStatement(sqlInsert);
            ps2.setInt(1, patientId);
            ps2.setInt(2, medicineId);
            ps2.executeUpdate();

            conn.commit();
            System.out.println("Giao dịch thành công!");

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.err.println("Giao dịch thất bại. Đã Rollback dữ liệu.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Main hospitalApp = new Main();
        hospitalApp.capPhatThuoc(101, 500);
    }
}