package SS8.bai5.command;

import SS8.bai5.device.AirConditioner;

public class ACSetTemperatureCommand implements Command {
    private AirConditioner ac;
    private int temp;
    public ACSetTemperatureCommand(AirConditioner ac, int temp) { this.ac = ac; this.temp = temp; }
    @Override
    public void execute() { ac.setTemperature(temp); }
}
