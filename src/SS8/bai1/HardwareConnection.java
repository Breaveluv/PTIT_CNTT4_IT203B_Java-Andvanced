package SS8.bai1;

public class HardwareConnection {
    private static HardwareConnection instance;

    private HardwareConnection() {
        System.out.println("HardwareConnection: Đã kết nối phần cứng.");
    }

    public static synchronized HardwareConnection getInstance() {
        if (instance == null) {
            instance = new HardwareConnection();
        }
        return instance;
    }

    public void connect() {
        System.out.println("Đang kết nối...");
    }

    public void disconnect() {
        System.out.println("Đã ngắt kết nối.");
    }
}
