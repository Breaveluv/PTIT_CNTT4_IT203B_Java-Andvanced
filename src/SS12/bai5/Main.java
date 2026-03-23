package SS12.bai5;

import java.sql.*;
import java.util.Scanner;

public class Main {
    // Thông số kết nối (Thay đổi theo DB của Nam)
    private static final String URL = "jdbc:mysql://localhost:3306/Rikkei_Hospital";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            while (true) {
                System.out.println("\n--- HỆ THỐNG QUẢN LÝ NỘI TRÚ RHMS ---");
                System.out.println("1. Danh sách bệnh nhân");
                System.out.println("2. Tiếp nhận bệnh nhân mới");
                System.out.println("3. Cập nhật bệnh án");
                System.out.println("4. Xuất viện & Tính phí");
                System.out.println("5. Thoát");
                System.out.print("Chọn chức năng: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1: showPatients(conn); break;
                    case 2: addPatient(conn, sc); break;
                    case 3: updateMedicalRecord(conn, sc); break;
                    case 4: dischargePatient(conn, sc); break;
                    case 5: System.out.println("Tạm biệt!"); return;
                    default: System.out.println("Lựa chọn không hợp lệ.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showPatients(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Patients";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nID | Tên | Tuổi | Khoa điều trị");
            while (rs.next()) {
                System.out.printf("%d | %s | %d | %s\n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getInt("age"), rs.getString("department"));
            }
        }
    }

    private static void addPatient(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Nhập tên bệnh nhân: ");
        String name = sc.nextLine();
        System.out.print("Nhập tuổi: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Khoa điều trị: ");
        String dept = sc.nextLine();

        String sql = "INSERT INTO Patients (name, age, department) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, dept);
            pstmt.executeUpdate();
            System.out.println("Thêm thành công!");
        }
    }

    // 3. Cập nhật bệnh án
    private static void updateMedicalRecord(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Nhập ID bệnh nhân: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Thông tin bệnh lý mới: ");
        String record = sc.nextLine();

        String sql = "UPDATE Patients SET medical_record = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, record);
            pstmt.setInt(2, id);
            if (pstmt.executeUpdate() > 0) System.out.println("Cập nhật thành công!");
            else System.out.println("Không tìm thấy ID.");
        }
    }

    // 4. Xuất viện & Tính phí (Gọi Stored Procedure)
    private static void dischargePatient(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Nhập ID bệnh nhân xuất viện: ");
        int id = sc.nextInt();

        String sql = "{call CALCULATE_DISCHARGE_FEE(?, ?)}";
        try (CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, id);
            cstmt.registerOutParameter(2, Types.DECIMAL);
            cstmt.execute();

            double totalFee = cstmt.getDouble(2);
            System.out.println("Bệnh nhân ID " + id + " đã xuất viện.");
            System.out.println("Tổng viện phí: " + totalFee + " VNĐ");
        }
    }
}