package SS12.bai4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Main {


    public void insertTestResults(Connection conn, List<TestResult> list) {

        String sql = "INSERT INTO Results(data) VALUES (?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (TestResult tr : list) {

                pstmt.setString(1, tr.getData());


                pstmt.executeUpdate();
            }

            System.out.println("Đã nạp thành công " + list.size() + " kết quả xét nghiệm.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


class TestResult {
    private String data;
    public String getData() { return data; }
}