package SS2.bai5;

interface UserActions {
    default void logActivity(String activity) {
        System.out.println("[User Log]: " + activity);
    }
}

interface AdminActions {
    default void logActivity(String activity) {
        System.out.println("[Admin Log]: " + activity);
    }
}

class SuperAdmin implements UserActions, AdminActions {

    @Override
    public void logActivity(String activity) {
        // Lựa chọn 1: Gọi logic của UserActions
        // UserActions.super.logActivity(activity);

        // Lựa chọn 2: Gọi logic của AdminActions (Phù hợp với SuperAdmin hơn)
        AdminActions.super.logActivity(activity);

        // Lựa chọn 3: Viết logic hoàn toàn mới
        System.out.println("[SuperAdmin Authority]: Ghi đè toàn bộ hoạt động cấp cao.");
    }
}

public class Main {
    public static void main(String[] args) {
        SuperAdmin sa = new SuperAdmin();

        sa.logActivity("Xóa lịch sử hệ thống");
    }
}