package SS4.bai2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final Main userService = new Main();

    @Test
    void testValidBoundaryAge_18() {
        // Arrange
        int age = 18;

        // Act
        userService.checkRegistrationAge(age);

        // Assert: Kiểm tra trạng thái thay vì nhận giá trị trả về
        assertEquals("SUCCESS", userService.registrationStatus, "Đúng 18 tuổi phải đăng ký thành công.");
    }

    @Test
    void testUnderAge_17() {
        // Arrange
        int age = 17;

        // Act
        userService.checkRegistrationAge(age);

        // Assert: Kiểm tra trạng thái không đủ tuổi
        assertEquals("UNDER_AGE", userService.registrationStatus, "17 tuổi phải báo trạng thái chưa đủ tuổi.");
    }

    @Test
    void testNegativeAge_Invalid() {
        // Arrange
        int age = -1;

        // Act
        userService.checkRegistrationAge(age);

        // Assert: Kiểm tra trạng thái dữ liệu lỗi (thay vì dùng assertThrows)
        assertEquals("INVALID_AGE", userService.registrationStatus, "Tuổi âm phải báo trạng thái không hợp lệ.");
    }
}