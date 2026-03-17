package SS8.bai3.command;

import SS8.bai3.device.Light;

public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
        System.out.println("Undo: Đèn Tắt (quay lại trạng thái trước)");
    }
    
    @Override
    public String toString() {
        return "LightOnCommand";
    }
}
