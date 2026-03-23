package SS12.bai3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class Main {
    public void getSurgeryFee(Connection conn) {

        int surgeryId = 505;


        String sql = "{call GET_SURGERY_FEE(?, ?)}";

        try (CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, surgeryId);


            cstmt.registerOutParameter(2, Types.DECIMAL);


            cstmt.execute();


            double totalCost = cstmt.getDouble(2);

            System.out.println("Tổng chi phí phẫu thuật cho ca " + surgeryId + " là: " + totalCost);

        } catch (SQLException e) {
            System.err.println("Lỗi khi truy cứu chi phí: " + e.getMessage());
            e.printStackTrace();
        }
    }
}