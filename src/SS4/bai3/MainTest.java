package SS4.bai3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Main userProcessor;

    @BeforeEach
    void setUp() {

        userProcessor = new Main();
    }

    @Test
    void testValidEmailFormat_SetsProcessedEmail() {
        // Arrange
        String input = "user@gmail.com";

        userProcessor.processEmail(input);

        assertEquals("user@gmail.com", userProcessor.processedEmail, "Email hợp lệ phải được lưu đúng.");
    }

    @Test
    void testEmailMissingAtSign_SetsErrorMessage() {
        String input = "usergmail.com";

        userProcessor.processEmail(input);

        assertEquals("INVALID_FORMAT", userProcessor.errorMessage, "Thiếu '@' phải báo lỗi định dạng.");
    }

    @Test
    void testEmailMissingDomain_SetsErrorMessage() {
        String input = "user@";

        userProcessor.processEmail(input);

        assertEquals("INVALID_FORMAT", userProcessor.errorMessage, "Thiếu tên miền phải báo lỗi định dạng.");
    }

    @Test
    void testEmailNormalization_SetsLowercaseEmail() {
        String input = "Example@Gmail.com";

        userProcessor.processEmail(input);

        assertEquals("example@gmail.com", userProcessor.processedEmail, "Email phải được chuẩn hóa về chữ thường.");
    }
}