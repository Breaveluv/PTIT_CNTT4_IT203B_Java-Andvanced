package SS2.bai3;

public class Main {
    public static void main(String[] args) {
        Authenticatable userAuth = () -> "mySecretPassword123";

        String raw = "123456";
        String hashed = Authenticatable.encrypt(raw);
        System.out.println("Mã hóa mật khẩu '" + raw + "': " + hashed);

        if (userAuth.isAuthenticated()) {
            System.out.println("Trạng thái: Đã xác thực thành công!");
            System.out.println("Mật khẩu lấy được: " + userAuth.getPassword());
        } else {
            System.out.println("Trạng thái: Xác thực thất bại (Mật khẩu trống).");
        }
    }
}
