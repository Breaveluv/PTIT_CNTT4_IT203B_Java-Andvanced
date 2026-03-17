package SS8.bai3.command;

import SS8.bai3.device.Fan;

public class FanOnCommand implements Command {
    private Fan fan;

    public FanOnCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.on();
    }

    @Override
    public void undo() {
        fan.off();
        System.out.println("Undo: Quạt Tắt (quay lại trạng thái trước)");
    }

    @Override
    public String toString() {
        return "FanOnCommand";
    }
}
