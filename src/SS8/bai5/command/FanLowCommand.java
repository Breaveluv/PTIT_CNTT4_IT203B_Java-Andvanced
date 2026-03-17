package SS8.bai5.command;

import SS8.bai5.device.Fan;

public class FanLowCommand implements Command {
    private Fan fan;
    public FanLowCommand(Fan fan) { this.fan = fan; }
    @Override
    public void execute() { fan.low(); }
}
