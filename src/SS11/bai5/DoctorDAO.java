package SS11.bai5;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private Connection conn;

    public DoctorDAO(Connection conn) { this.conn = conn; }

    public List<Doctor> getAll() throws SQLException {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            list.add(new Doctor(rs.getString("doctor_id"), rs.getString("full_name"), rs.getString("specialization")));
        }
        return list;
    }

    public boolean insert(Doctor d) throws SQLException {
        String sql = "INSERT INTO Doctors (doctor_id, full_name, specialization) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, d.getDoctorId());
            pstmt.setString(2, d.getFullName());
            pstmt.setString(3, d.getSpecialization());
            return pstmt.executeUpdate() > 0;
        }
    }

    public void reportBySpecialization() throws SQLException {
        String sql = "SELECT specialization, COUNT(*) as total FROM Doctors GROUP BY specialization";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("--- Thống kê chuyên khoa ---");
        while (rs.next()) {
            System.out.printf("%-20s: %d bác sĩ\n", rs.getString("specialization"), rs.getInt("total"));
        }
    }
}