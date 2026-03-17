package SS8.bai3.command;

import SS8.bai3.device.Fan;

public class FanOffCommand implements Command {
    private Fan fan;

    public FanOffCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.off();
    }

    @Override
    public void undo() {
        fan.on();
        System.out.println("Undo: Quạt Bật (quay lại trạng thái trước)");
    }

    @Override
    public String toString() {
        return "FanOffCommand";
    }
}
