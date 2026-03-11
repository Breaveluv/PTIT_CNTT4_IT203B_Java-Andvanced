package SS4.bai4;

public class Main {
    public String strengthResult = "";

    public void evaluatePasswordStrength(String password) {
        if (password == null || password.length() < 8) {
            strengthResult = "Yếu";
            return;
        }

        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        if (hasUpper && hasLower && hasDigit && hasSpecial) {
            strengthResult = "Mạnh";
        } else if ((hasUpper || hasLower) && hasDigit) {
            strengthResult = "Trung bình";
        } else {
            strengthResult = "Yếu";
        }
    }
}