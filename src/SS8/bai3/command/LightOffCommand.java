package SS8.bai3.command;

import SS8.bai3.device.Light;

public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
        System.out.println("Undo: Đèn Bật (quay lại trạng thái trước)");
    }

    @Override
    public String toString() {
        return "LightOffCommand";
    }
}
