package SS13.bai2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/Rikkei_Hospital";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public void thanhToanVienPhi(int patientId, int invoiceId, double amount) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);

            conn.setAutoCommit(false);

            String sqlDeductWallet = "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sqlDeductWallet);
            ps1.setDouble(1, amount);
            ps1.setInt(2, patientId);
            ps1.executeUpdate();


            String sqlUpdateInvoice = "UPDATE Invoices SET status = 'PAID' WHERE invoice_id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sqlUpdateInvoice);
            ps2.setInt(1, invoiceId);
            ps2.executeUpdate();


            conn.commit();
            System.out.println("Thanh toán hoàn tất!");

        } catch (SQLException e) {
            System.err.println("Lỗi hệ thống: Không thể hoàn tất thanh toán. Chi tiết: " + e.getMessage());

            if (conn != null) {
                try {

                    conn.rollback();
                    System.out.println("Đã khôi phục (rollback) dữ liệu về trạng thái an toàn.");
                } catch (SQLException ex) {
                    System.err.println("Lỗi nghiêm trọng khi thực hiện Rollback: " + ex.getMessage());
                }
            }
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.thanhToanVienPhi(1, 100, 500.0);
    }
}