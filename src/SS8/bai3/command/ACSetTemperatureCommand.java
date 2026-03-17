package SS8.bai3.command;

import SS8.bai3.device.AirConditioner;

public class ACSetTemperatureCommand implements Command {
    private AirConditioner ac;
    private int temperature;
    private int prevTemperature;

    public ACSetTemperatureCommand(AirConditioner ac, int temperature) {
        this.ac = ac;
        this.temperature = temperature;
    }

    @Override
    public void execute() {
        prevTemperature = ac.getTemperature();
        ac.setTemperature(temperature);
    }

    @Override
    public void undo() {
        ac.setTemperature(prevTemperature);
        System.out.println("Undo: Điều hòa: Nhiệt độ = " + prevTemperature + " (nhiệt độ cũ)");
    }

    @Override
    public String toString() {
        return "ACSetTempCommand(" + temperature + ")";
    }
}
