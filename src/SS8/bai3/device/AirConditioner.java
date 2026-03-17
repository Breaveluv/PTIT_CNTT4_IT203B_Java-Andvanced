package SS8.bai3.device;

public class AirConditioner {
    private String location;
    private int temperature;

    public AirConditioner(String location) {
        this.location = location;
        this.temperature = 25; // Default temperature
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println(location + " Điều hòa: Nhiệt độ = " + temperature);
    }

    public int getTemperature() {
        return temperature;
    }
}
