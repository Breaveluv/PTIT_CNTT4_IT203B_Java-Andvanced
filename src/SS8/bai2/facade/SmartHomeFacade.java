package SS8.bai2.facade;

import SS8.bai2.adapter.TemperatureSensor;
import SS8.bai2.devices.AirConditioner;
import SS8.bai2.devices.Fan;
import SS8.bai2.devices.Light;

public class SmartHomeFacade {
    private Light light;
    private Fan fan;
    private AirConditioner airConditioner;
    private TemperatureSensor temperatureSensor;

    public SmartHomeFacade(Light light, Fan fan, AirConditioner airConditioner, TemperatureSensor temperatureSensor) {
        this.light = light;
        this.fan = fan;
        this.airConditioner = airConditioner;
        this.temperatureSensor = temperatureSensor;
    }

    public void leaveHome() {
        light.turnOff();
        fan.turnOff();
        airConditioner.turnOff();
    }

    public void sleepMode() {
        light.turnOff();
        airConditioner.setTemperature(28);
        fan.setLowSpeed();
    }

    public double getCurrentTemperature() {
        return temperatureSensor.getTemperatureCelsius();
    }
}
