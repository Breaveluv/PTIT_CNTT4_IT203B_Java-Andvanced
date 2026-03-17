package SS8.bai5;

import SS8.bai5.command.ACSetTemperatureCommand;
import SS8.bai5.command.FanLowCommand;
import SS8.bai5.command.LightOffCommand;
import SS8.bai5.command.SleepModeCommand;
import SS8.bai5.device.AirConditioner;
import SS8.bai5.device.Fan;
import SS8.bai5.device.Light;
import SS8.bai5.observer.TemperatureSensor;

public class Main {
    public static void main(String[] args) {
        // --- Setup ---
        // Devices (Receivers)
        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        // Subject
        TemperatureSensor sensor = new TemperatureSensor();
        
        // Attach observers to the sensor
        sensor.attach(fan);
        sensor.attach(ac);

        // --- Create Macro Command for Sleep Mode ---
        SleepModeCommand sleepMode = new SleepModeCommand();
        sleepMode.addCommand(new LightOffCommand(light), "Tắt đèn");
        sleepMode.addCommand(new ACSetTemperatureCommand(ac, 28), "Điều hòa set 28°C");
        sleepMode.addCommand(new FanLowCommand(fan), "Quạt tốc độ thấp");

        // --- Simulation ---
        // 1. Activate Sleep Mode
        System.out.println("1. Kích hoạt chế độ ngủ");
        System.out.println();
        sleepMode.execute();

        System.out.println("\n2. Nhiệt độ tăng lên 32°C (giả lập)");
        System.out.println();
        // 2. Simulate temperature change
        sensor.setTemperature(32);
    }
}
