
// Command Interface
interface Command {
    void execute();

    void undo();
}

// CeilingFan Class (Receiver)
class CeilingFan {
    private int speed;
    public static final int HIGH = 3;
    public static final int MEDIUM = 2;
    public static final int LOW = 1;
    public static final int OFF = 0;

    public CeilingFan() {
        speed = OFF;
    }

    public void high() {
        speed = HIGH;
    }

    public void medium() {
        speed = MEDIUM;

    }

    public void low() {
        speed = LOW;

    }

    public void off() {
        speed = OFF;

    }

    public int getSpeed() {
        return speed;
    }

    public void showSpeed() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_BLUE = "\u001B[34m";

        StringBuilder loadingBar = new StringBuilder("");
        String color = ANSI_RESET;

        switch (speed) {
            case HIGH:
                color = ANSI_RED;
                // loadingBar.append(color).append("HIGH").append(ANSI_RESET);
                loadingBar.append(color);
                for (int i = 1; i < 20; i++) {
                    loadingBar.append("|");
                }
                loadingBar.append(ANSI_RESET);
                break;
            case MEDIUM:
                color = ANSI_YELLOW;
                loadingBar.append(color);
                for (int i = 1; i < 15; i++) {
                    loadingBar.append("|");
                }
                loadingBar.append(ANSI_RESET);
                break;
            case LOW:
                color = ANSI_GREEN;
                loadingBar.append(color);
                for (int i = 1; i < 8; i++) {
                    loadingBar.append("|");
                }
                loadingBar.append(ANSI_RESET);
                break;
            case OFF:
                color = ANSI_BLUE;
                loadingBar.append(color);
                for (int i = 1; i < 4; i++) {
                    loadingBar.append("|");
                }
                loadingBar.append(ANSI_RESET);
                break;
        }
        System.out.println("-------------------");
        System.out.println("OFF LOW MEDIUM HIGH");
        
        System.out.println(loadingBar.toString());
        System.out.println("-------------------");
    }
}

// Concrete Commands
class CeilingFanHighCommand implements Command {
    private CeilingFan ceilingFan;
    private int prevSpeed;

    public CeilingFanHighCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.high();
        ceilingFan.showSpeed();
    }

    @Override
    public void undo() {
        if (prevSpeed == CeilingFan.HIGH) {
            ceilingFan.high();
        } else if (prevSpeed == CeilingFan.MEDIUM) {
            ceilingFan.medium();
        } else if (prevSpeed == CeilingFan.LOW) {
            ceilingFan.low();
        } else if (prevSpeed == CeilingFan.OFF) {
            ceilingFan.off();
        }
        ceilingFan.showSpeed();
    }
}

class CeilingFanMediumCommand implements Command {
    private CeilingFan ceilingFan;
    private int prevSpeed;

    public CeilingFanMediumCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.medium();
        ceilingFan.showSpeed();
    }

    @Override
    public void undo() {
        if (prevSpeed == CeilingFan.HIGH) {
            ceilingFan.high();
        } else if (prevSpeed == CeilingFan.MEDIUM) {
            ceilingFan.medium();
        } else if (prevSpeed == CeilingFan.LOW) {
            ceilingFan.low();
        } else if (prevSpeed == CeilingFan.OFF) {
            ceilingFan.off();
        }
        ceilingFan.showSpeed();
    }
}

class CeilingFanLowCommand implements Command {
    private CeilingFan ceilingFan;
    private int prevSpeed;

    public CeilingFanLowCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.low();
        ceilingFan.showSpeed();
    }

    @Override
    public void undo() {
        if (prevSpeed == CeilingFan.HIGH) {
            ceilingFan.high();
        } else if (prevSpeed == CeilingFan.MEDIUM) {
            ceilingFan.medium();
        } else if (prevSpeed == CeilingFan.LOW) {
            ceilingFan.low();
        } else if (prevSpeed == CeilingFan.OFF) {
            ceilingFan.off();
        }
        ceilingFan.showSpeed();
    }
}

class CeilingFanOffCommand implements Command {
    private CeilingFan ceilingFan;
    private int prevSpeed;

    public CeilingFanOffCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.off();
        ceilingFan.showSpeed();
    }

    @Override
    public void undo() {
        if (prevSpeed == CeilingFan.HIGH) {
            ceilingFan.high();
        } else if (prevSpeed == CeilingFan.MEDIUM) {
            ceilingFan.medium();
        } else if (prevSpeed == CeilingFan.LOW) {
            ceilingFan.low();
        } else if (prevSpeed == CeilingFan.OFF) {
            ceilingFan.off();
        }
        ceilingFan.showSpeed();
    }
}

// NoCommand Class
class NoCommand implements Command {
    @Override
    public void execute() {
        // Do nothing
    }

    @Override
    public void undo() {
        // Do nothing
    }
}

// RemoteControl Class (Invoker)
class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private Command undoCommand;

    public RemoteControl() {
        onCommands = new Command[4];
        offCommands = new Command[4];

        Command noCommand = new NoCommand();
        for (int i = 0; i < 4; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    public void undoButtonWasPushed() {
        undoCommand.undo();
    }
}

// Client
public class CeilingFanTest {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();
        CeilingFan ceilingFan = new CeilingFan();

        CeilingFanHighCommand ceilingFanHigh = new CeilingFanHighCommand(ceilingFan);
        CeilingFanMediumCommand ceilingFanMedium = new CeilingFanMediumCommand(ceilingFan);
        CeilingFanLowCommand ceilingFanLow = new CeilingFanLowCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(ceilingFan);

        remoteControl.setCommand(0, ceilingFanHigh, ceilingFanOff);
        remoteControl.setCommand(1, ceilingFanMedium, ceilingFanOff);
        remoteControl.setCommand(2, ceilingFanLow, ceilingFanOff);

        // Test the commands
        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        remoteControl.undoButtonWasPushed();

        remoteControl.onButtonWasPushed(1);
        remoteControl.undoButtonWasPushed();

        remoteControl.onButtonWasPushed(2);
        remoteControl.undoButtonWasPushed();
    }
}