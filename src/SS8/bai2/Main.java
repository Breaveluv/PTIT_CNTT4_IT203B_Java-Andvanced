package SS8.bai2;

import SS8.bai2.adapter.OldThermometer;
import SS8.bai2.adapter.TemperatureSensor;
import SS8.bai2.adapter.ThermometerAdapter;
import SS8.bai2.devices.AirConditioner;
import SS8.bai2.devices.Fan;
import SS8.bai2.devices.Light;
import SS8.bai2.facade.SmartHomeFacade;

public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner airConditioner = new AirConditioner();
        
        // Khởi tạo cảm biến cũ và adapter
        OldThermometer oldThermometer = new OldThermometer();
        TemperatureSensor temperatureSensor = new ThermometerAdapter(oldThermometer);
        
        // Khởi tạo Facade
        SmartHomeFacade smartHome = new SmartHomeFacade(light, fan, airConditioner, temperatureSensor);

        // 1. Xem nhiệt độ
        System.out.println("1. Xem nhiệt độ");
        System.out.println();
        System.out.printf("Nhiệt độ hiện tại: %.1f°C (chuyển đổi từ %d°F)\n", 
                smartHome.getCurrentTemperature(), 
                oldThermometer.getTemperatureFahrenheit());
        
        // 2. Chế độ rời nhà
        System.out.println("\n2. Chế độ rời nhà");
        System.out.println();
        smartHome.leaveHome();
        
        // 3. Chế độ ngủ
        System.out.println("\n3. Chế độ ngủ");
        System.out.println();
        smartHome.sleepMode();
    }
}
