package SS8.bai4;

import java.util.ArrayList;
import java.util.List;

public class TemperatureSensor implements Subject {
    private List<Observer> observers;
    private int temperature;

    public TemperatureSensor() {
        this.observers = new ArrayList<>();
    }

    public void setTemperature(int temperature) {
        System.out.println("Cảm biến: Nhiệt độ = " + temperature);
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
        // Assuming we want to print a confirmation message, although not strictly required by interface
        // The requirement output shows "Quạt: Đã đăng ký nhận thông báo" which likely comes from the Fan itself or main method
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature);
        }
    }
}
