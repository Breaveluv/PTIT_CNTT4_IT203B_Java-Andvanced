package SS8.bai5.command;

import java.util.ArrayList;
import java.util.List;

public class SleepModeCommand implements Command {
    private List<Command> commands;
    private List<String> commandNames; // To store descriptive names

    public SleepModeCommand() {
        commands = new ArrayList<>();
        commandNames = new ArrayList<>();
    }

    public void addCommand(Command command, String name) {
        commands.add(command);
        commandNames.add(name);
    }

    @Override
    public void execute() {
        // First, print the descriptive plan
        for (String name : commandNames) {
            System.out.println("SleepMode: " + name);
        }
        // Then, execute the commands to get the device feedback
        for (Command command : commands) {
            command.execute();
        }
    }
}
