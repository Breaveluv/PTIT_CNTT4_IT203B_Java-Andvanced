package SS8.bai5.command;

import SS8.bai5.device.Light;

public class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) { this.light = light; }
    @Override
    public void execute() { light.off(); }
}
