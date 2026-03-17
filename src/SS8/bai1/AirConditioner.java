package SS8.bai1;

public class AirConditioner implements Device {
    @Override
    public void turnOn() {
        System.out.println("Điều hòa: Đã bật.");
    }

    @Override
    public void turnOff() {
        System.out.println("Điều hòa: Đã tắt.");
    }
}
