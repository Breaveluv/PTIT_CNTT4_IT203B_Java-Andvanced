package SS8.bai3.invoker;

import SS8.bai3.command.Command;
import java.util.Stack;

public class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private Stack<Command> undoStack;

    public RemoteControl() {
        onCommands = new Command[5];
        offCommands = new Command[5];
        undoStack = new Stack<>();

        Command noCommand = new NoCommand();
        for (int i = 0; i < 5; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
        System.out.println("Đã gán " + onCommand + " cho nút " + (slot + 1) + " (On)");
        System.out.println("Đã gán " + offCommand + " cho nút " + (slot + 1) + " (Off)");
    }
    
    // Simplified version as per requirement: "Gán nút 1: Bật đèn"
    public void setCommand(int slot, Command command) {
        if (slot >= 0 && slot < onCommands.length) {
            onCommands[slot] = command;
            System.out.println("Đã gán " + command + " cho nút " + (slot + 1));
        }
    }

    public void onButtonWasPushed(int slot) {
        if (slot >= 0 && slot < onCommands.length) {
            onCommands[slot].execute();
            undoStack.push(onCommands[slot]);
        }
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
        undoStack.push(offCommands[slot]);
    }

    public void undoButtonWasPushed() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
        } else {
            System.out.println("Nothing to undo");
        }
    }
    
    // Inner class for initialization
    private class NoCommand implements Command {
        public void execute() {}
        public void undo() {}
        public String toString() { return "NoCommand"; }
    }
}
