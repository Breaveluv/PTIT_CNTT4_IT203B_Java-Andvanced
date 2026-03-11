package SS4.bai4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final Main validator = new Main();

    @Test
    void testPasswordStrengthScenarios() {
        assertAll("Kiểm tra các kịch bản mật khẩu khác nhau",
                () -> {
                    // Kịch bản: Mật khẩu Mạnh (Đầy đủ tiêu chí)
                    validator.evaluatePasswordStrength("Admin@123");
                    assertEquals("Mạnh", validator.strengthResult, "Mật khẩu đầy đủ ký tự phải là Mạnh.");
                },
                () -> {
                    // Kịch bản: Thiếu ký tự đặc biệt
                    validator.evaluatePasswordStrength("Admin123");
                    assertEquals("Trung bình", validator.strengthResult, "Thiếu ký tự đặc biệt nên là Trung bình.");
                },
                () -> {
                    // Kịch bản: Quá ngắn (< 8 ký tự)
                    validator.evaluatePasswordStrength("Ab1!");
                    assertEquals("Yếu", validator.strengthResult, "Mật khẩu quá ngắn phải là Yếu.");
                },
                () -> {
                    // Kịch bản: Thiếu chữ hoa
                    validator.evaluatePasswordStrength("admin@123");
                    assertNotEquals("Mạnh", validator.strengthResult, "Thiếu chữ hoa không thể là Mạnh.");
                }
        );
    }
}