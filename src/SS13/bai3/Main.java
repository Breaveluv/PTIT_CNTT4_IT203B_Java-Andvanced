package SS13.bai3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/Rikkei_Hospital";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public void xuatVienVaThanhToan(int maBenhNhan, double tienVienPhi) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            // Kích hoạt Transaction thủ công
            conn.setAutoCommit(false);

            // --- BẪY 1: KIỂM TRA LOGIC NGHIỆP VỤ (THIẾU TIỀN) ---
            String sqlCheckBalance = "SELECT balance FROM Patient_Wallet WHERE patient_id = ?";
            PreparedStatement psCheck = conn.prepareStatement(sqlCheckBalance);
            psCheck.setInt(1, maBenhNhan);
            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                double soDuHienTai = rs.getDouble("balance");
                if (soDuHienTai < tienVienPhi) {
                    throw new SQLException("Lỗi: Số dư tạm ứng không đủ để thanh toán viện phí!");
                }
            } else {
                throw new SQLException("Lỗi: Không tìm thấy ví của bệnh nhân ID " + maBenhNhan);
            }

            // --- THỰC THI BƯỚC 1: TRỪ TIỀN VIỆN PHÍ ---
            String sqlDeduct = "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sqlDeduct);
            ps1.setDouble(1, tienVienPhi);
            ps1.setInt(2, maBenhNhan);
            ps1.executeUpdate();

            // --- THỰC THI BƯỚC 2: GIẢI PHÓNG GIƯỜNG BỆNH ---
            String sqlReleaseBed = "UPDATE Beds SET status = 'EMPTY' WHERE patient_id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sqlReleaseBed);
            ps2.setInt(1, maBenhNhan);
            int rowBed = ps2.executeUpdate();

            // --- BẪY 2: KIỂM TRA DỮ LIỆU ẢO (ROW AFFECTED = 0) ---
            if (rowBed == 0) {
                throw new SQLException("Lỗi: Không thể giải phóng giường. Có thể bệnh nhân chưa được gán giường hoặc ID sai.");
            }

            // --- THỰC THI BƯỚC 3: CẬP NHẬT TRẠNG THÁI BỆNH NHÂN ---
            String sqlUpdatePatient = "UPDATE Patients SET status = 'DISCHARGED' WHERE id = ?";
            PreparedStatement ps3 = conn.prepareStatement(sqlUpdatePatient);
            ps3.setInt(1, maBenhNhan);
            int rowPatient = ps3.executeUpdate();

            // --- BẪY 2: TIẾP TỤC KIỂM TRA DỮ LIỆU ẢO ---
            if (rowPatient == 0) {
                throw new SQLException("Lỗi: Không thể cập nhật trạng thái xuất viện cho bệnh nhân ID " + maBenhNhan);
            }

            // NẾU ĐẾN ĐÂY KHÔNG CÓ LỖI -> COMMIT TẤT CẢ
            conn.commit();
            System.out.println("Chúc mừng! Bệnh nhân " + maBenhNhan + " đã hoàn tất thủ tục xuất viện an toàn.");

        } catch (SQLException e) {
            // NẾU CÓ BẤT KỲ LỖI NÀO (BAO GỒM CẢ CÁC BẪY) -> ROLLBACK NGAY
            if (conn != null) {
                try {
                    conn.rollback();
                    System.err.println("Transaction bị hủy bỏ (Rollback). Lý do: " + e.getMessage());
                } catch (SQLException ex) {
                    ex.printStackTrace();
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
        Main service = new Main();
        service.xuatVienVaThanhToan(254, 1500000.0);
    }
}