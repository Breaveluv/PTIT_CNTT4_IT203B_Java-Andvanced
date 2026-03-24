package SS11.bai5;


import java.sql.*;
import java.util.Scanner;

public class RikkeiCareApp {
    private static final String URL = "jdbc:mysql://localhost:3306/RikkeiCare";
    private static final  String user = "root";
    private static final  String password = "123456";


    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, user, password)) {
            DoctorDAO dao = new DoctorDAO(conn);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n--- HỆ THỐNG RIKKEI-CARE ---");
                System.out.println("1. Xem danh sách bác sĩ");
                System.out.println("2. Thêm bác sĩ mới");
                System.out.println("3. Thống kê chuyên khoa");
                System.out.println("4. Thoát");
                System.out.print("Chọn chức năng: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        dao.getAll().forEach(d -> System.out.println(d.getDoctorId() + " | " + d.getFullName() + " | " + d.getSpecialization()));
                        break;
                    case 2:
                        System.out.print("Nhập mã BS: "); String id = sc.nextLine();
                        System.out.print("Nhập họ tên: "); String name = sc.nextLine();
                        System.out.print("Nhập chuyên khoa: "); String spec = sc.nextLine();
                        if(dao.insert(new Doctor(id, name, spec))) System.out.println("Thêm thành công!");
                        break;
                    case 3:
                        dao.reportBySpecialization();
                        break;
                    case 4:
                        System.exit(0);
                }
            }
        } catch (Exception e) {
            System.err.println("Lỗi hệ thống: " + e.getMessage());
        }
    }
}