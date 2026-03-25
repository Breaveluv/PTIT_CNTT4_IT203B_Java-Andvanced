package KTĐGSS14.Account;

import java.sql.*;

public class BankDB {
    private static final String URL = "jdbc:mysql://localhost:3306/BankDB";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public static void main(String[] args) {
        String fromId = "ACC01";
        String toId = "ACC02";
        double amount = 1000;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            conn.setAutoCommit(false);

            try {

                String checkSql = "SELECT Balance FROM Accounts WHERE AccountId = ?";
                try (PreparedStatement pstmtCheck = conn.prepareStatement(checkSql)) {
                    pstmtCheck.setString(1, fromId);
                    ResultSet rs = pstmtCheck.executeQuery();

                    if (rs.next()) {
                        double currentBalance = rs.getDouble("Balance");
                        if (currentBalance < amount) {
                            throw new Exception("Số dư không đủ để thực hiện giao dịch!");
                        }
                    } else {
                        throw new Exception("Tài khoản gửi không tồn tại!");
                    }
                }


                try (CallableStatement cstmt = conn.prepareCall("{call sp_UpdateBalance(?, ?)}")) {

                    cstmt.setString(1, fromId);
                    cstmt.setDouble(2, -amount);
                    cstmt.execute();


                    cstmt.setString(1, toId);
                    cstmt.setDouble(2, amount);
                    cstmt.execute();
                }

                conn.commit();
                System.out.println("Giao dịch chuyển khoản thành công!");

                displayAccounts(conn);

            } catch (Exception e) {
                conn.rollback();
                System.err.println("Giao dịch thất bại, đã Rollback. Lý do: " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayAccounts(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Accounts";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("\n--- BẢNG KẾT QUẢ ĐỐI SOÁT ---");
            System.out.printf("%-10s | %-15s | %-10s\n", "ID", "Họ Tên", "Số Dư");
            System.out.println("------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-10s | %-15s | %-10.2f\n",
                        rs.getString("AccountId"),
                        rs.getString("FullName"),
                        rs.getDouble("Balance"));
            }
        }
    }
}