package SS8.bai3.device;

public class Light {
    private String location;
    private boolean isOn;

    public Light(String location) {
        this.location = location;
        this.isOn = false;
    }

    public void on() {
        isOn = true;
        System.out.println(location + " Đèn: Bật");
    }

    public void off() {
        isOn = false;
        System.out.println(location + " Đèn: Tắt");
    }

    public boolean isOn() {
        return isOn;
    }
}
