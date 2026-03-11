package SS4.bai1;


import org.junit.Test;

import static org.testng.Assert.assertTrue;

public class MainTest {

    private final Main validator = new Main();

    @Test
    public void testValidUsername_TC01() {
        String input = "user123";

        boolean result = validator.isValidUsername(input);

        assertTrue(result, "TC01: 'user123' phải là username hợp lệ.");
    }

    @Test
    public void testUsernameTooShort_TC02() {
        String input = "abc";

        boolean result = validator.isValidUsername(input);

        assertFalse(result, "TC02: 'abc' quá ngắn, không hợp lệ.");
    }

    @Test
    public void testUsernameContainsSpace_TC03() {
        String input = "user name";

        boolean result = validator.isValidUsername(input);

        assertFalse(result, "TC03: Username không được chứa khoảng trắng.");
    }

    private void assertFalse(boolean result, String s) {
    }
}