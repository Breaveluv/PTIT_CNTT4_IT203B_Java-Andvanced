package SS8.bai1;

public class Fan implements Device {
    @Override
    public void turnOn() {
        System.out.println("Quạt: Đã bật.");
    }

    @Override
    public void turnOff() {
        System.out.println("Quạt: Đã tắt.");
    }
}
