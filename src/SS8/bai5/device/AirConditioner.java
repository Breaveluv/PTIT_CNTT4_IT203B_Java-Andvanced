package SS8.bai5.device;

import SS8.bai5.observer.Observer;

public class AirConditioner implements Observer {
    public void setTemperature(int temp) {
        System.out.println("Điều hòa: Nhiệt độ = " + temp);
    }

    @Override
    public void update(int temperature) {
        // Simple logic for AC to adjust or just print status
    }
}
