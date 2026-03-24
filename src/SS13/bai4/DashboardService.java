package SS13.bai4;

import java.sql.*;
import java.util.*;

public class DashboardService {
    private static final String URL = "jdbc:mysql://localhost:3306/Rikkei_Hospital";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public List<BenhNhanDTO> getDashboardData(String ngayHienTai) {
        Map<Integer, BenhNhanDTO> patientMap = new LinkedHashMap<>();

        String sql = "SELECT b.id, b.ten_benh_nhan, b.tuoi, d.id as dv_id, d.ten_dich_vu " +
                "FROM BenhNhan b " +
                "LEFT JOIN DichVuSuDung d ON b.id = d.ma_benh_nhan " +
                "WHERE b.ngay_nhap_vien = ?";

        // Sử dụng try-with-resources để tự động đóng Connection và PreparedStatement
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ngayHienTai);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int patientId = rs.getInt("id");

                    // Nếu bệnh nhân chưa có trong Map thì tạo mới
                    BenhNhanDTO dto = patientMap.get(patientId);
                    if (dto == null) {
                        dto = new BenhNhanDTO();
                        dto.setId(patientId);
                        dto.setTenBenhNhan(rs.getString("ten_benh_nhan"));
                        dto.setTuoi(rs.getInt("tuoi"));
                        dto.setDsDichVu(new ArrayList<>());
                        patientMap.put(patientId, dto);
                    }

                    // Xử lý Bẫy 2: Kiểm tra dịch vụ có tồn tại hay không (do dùng LEFT JOIN)
                    int dichVuId = rs.getInt("dv_id");
                    if (!rs.wasNull()) {
                        DichVu dv = new DichVu();
                        dv.setId(dichVuId);
                        dv.setTenDichVu(rs.getString("ten_dich_vu"));
                        dto.getDsDichVu().add(dv);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn Dashboard: " + e.getMessage());
        }

        return new ArrayList<>(patientMap.values());
    }
}