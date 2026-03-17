package SS8.bai3;

import SS8.bai3.command.ACSetTemperatureCommand;
import SS8.bai3.command.LightOffCommand;
import SS8.bai3.command.LightOnCommand;
import SS8.bai3.device.AirConditioner;
import SS8.bai3.device.Light;
import SS8.bai3.invoker.RemoteControl;

public class Main {
    public static void main(String[] args) {
        // Create the invoker
        RemoteControl remote = new RemoteControl();

        // Create devices (receivers)
        Light livingRoomLight = new Light("Phòng khách");
        AirConditioner livingRoomAC = new AirConditioner("Phòng khách");

        // --- Scenario 1: Light On/Off and Undo ---
        System.out.println("--- Kịch bản 1: Bật/Tắt đèn và Undo ---");

        // Create commands
        LightOnCommand lightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand lightOff = new LightOffCommand(livingRoomLight);

        // Assign command to button 1
        remote.setCommand(0, lightOn);
        System.out.print("Nhấn nút 1: ");
        remote.onButtonWasPushed(0);

        // Assign command to button 2
        remote.setCommand(1, lightOff);
        System.out.print("Nhấn nút 2: ");
        remote.onButtonWasPushed(1);

        // Undo the last action (Light Off)
        System.out.print("Nhấn Undo: ");
        remote.undoButtonWasPushed();
        
        System.out.println("\n--- Kịch bản 2: Điều khiển điều hòa và Undo ---");

        // --- Scenario 2: AC Temp and Undo ---
        System.out.println("Nhiệt độ ban đầu của điều hòa: " + livingRoomAC.getTemperature());
        
        // Create command to set temperature to 26
        ACSetTemperatureCommand setTemp26 = new ACSetTemperatureCommand(livingRoomAC, 26);
        
        // Assign command to button 3
        remote.setCommand(2, setTemp26);
        System.out.print("Nhấn nút 3: ");
        remote.onButtonWasPushed(2);

        // Undo the temperature change
        System.out.print("Nhấn Undo: ");
        remote.undoButtonWasPushed();
    }
}
