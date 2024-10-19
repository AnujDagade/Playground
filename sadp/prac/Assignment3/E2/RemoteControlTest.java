// Command Interface
interface Command {
    void execute();
    void undo();
}

// Light class
class Light {
    String location;

    public Light(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println(location + " light is ON");
    }

    public void off() {
        System.out.println(location + " light is OFF");
    }
}

// LightOnCommand class
class LightOnCommand implements Command {
    Light light;

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
    }
}

// LightOffCommand class
class LightOffCommand implements Command {
    Light light;

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
    }
}

// GarageDoor class
class GarageDoor {
    public void up() {
        System.out.println("Garage Door is Open");
    }

    public void down() {
        System.out.println("Garage Door is Closed");
    }
}

// GarageDoorOpenCommand class
class GarageDoorOpenCommand implements Command {
    GarageDoor garageDoor;

    public GarageDoorOpenCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.up();
    }

    @Override
    public void undo() {
        garageDoor.down();
    }
}

// GarageDoorCloseCommand class
class GarageDoorCloseCommand implements Command {
    GarageDoor garageDoor;

    public GarageDoorCloseCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.down();
    }

    @Override
    public void undo() {
        garageDoor.up();
    }
}

// SimpleRemoteControl class
class SimpleRemoteControl {
    Command slot;
    
    public SimpleRemoteControl() {}

    public void setCommand(Command command) {
        this.slot = command;
    }

    public void buttonWasPressed() {
        slot.execute();
    }

    public void undoButtonWasPressed() {
        slot.undo();
    }
}

// RemoteControlTest class
public class RemoteControlTest {
    public static void main(String[] args) {
        // Create Remote Control
        SimpleRemoteControl remote = new SimpleRemoteControl();

        // Create Devices
        Light livingRoomLight = new Light("Living Room");
        GarageDoor garageDoor = new GarageDoor();

        // Create Command objects
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
        GarageDoorOpenCommand garageDoorOpen = new GarageDoorOpenCommand(garageDoor);
        GarageDoorCloseCommand garageDoorClose = new GarageDoorCloseCommand(garageDoor);

        // Test Light On Command
        remote.setCommand(livingRoomLightOn);
        remote.buttonWasPressed();
        remote.undoButtonWasPressed();

        // Test Light Off Command
        remote.setCommand(livingRoomLightOff);
        remote.buttonWasPressed();
        remote.undoButtonWasPressed();

        // Test Garage Door Open Command
        remote.setCommand(garageDoorOpen);
        remote.buttonWasPressed();
        remote.undoButtonWasPressed();

        // Test Garage Door Close Command
        remote.setCommand(garageDoorClose);
        remote.buttonWasPressed();
        remote.undoButtonWasPressed();
    }
}
