package SS8.bai4;

public class Main {
    public static void main(String[] args) {
        // Create the Subject
        TemperatureSensor sensor = new TemperatureSensor();

        // Create Observers
        Fan fan = new Fan();
        Humidifier humidifier = new Humidifier();

        // Register observers
        sensor.attach(fan);
        System.out.println("Quạt: Đã đăng ký nhận thông báo");
        sensor.attach(humidifier);
        System.out.println("Máy tạo ẩm: Đã đăng ký");

        System.out.println("\n--- Thay đổi nhiệt độ lần 1 ---");
        // Simulate temperature change
        sensor.setTemperature(18);

        System.out.println("\n--- Thay đổi nhiệt độ lần 2 ---");
        // Simulate another temperature change
        sensor.setTemperature(26);
        
        System.out.println("\n--- Hủy đăng ký quạt và thay đổi nhiệt độ ---");
        sensor.detach(fan);
        System.out.println("Quạt: Đã hủy đăng ký");
        sensor.setTemperature(22);
    }
}
