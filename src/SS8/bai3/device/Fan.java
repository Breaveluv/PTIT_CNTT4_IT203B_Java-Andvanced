package SS8.bai3.device;

public class Fan {
    private String location;
    private boolean isOn;

    public Fan(String location) {
        this.location = location;
        this.isOn = false;
    }

    public void on() {
        isOn = true;
        System.out.println(location + " Quạt: Bật");
    }

    public void off() {
        isOn = false;
        System.out.println(location + " Quạt: Tắt");
    }

    public boolean isOn() {
        return isOn;
    }
}
