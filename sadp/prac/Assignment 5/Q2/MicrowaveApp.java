import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Command Pattern
interface Command {
    void execute();
}

class StartCookingCommand implements Command {
    private Microwave microwave;

    public StartCookingCommand(Microwave microwave) {
        this.microwave = microwave;
    }

    @Override
    public void execute() {
        microwave.startCooking();
    }
}

class PauseCookingCommand implements Command {
    private Microwave microwave;

    public PauseCookingCommand(Microwave microwave) {
        this.microwave = microwave;
    }

    @Override
    public void execute() {
        microwave.pauseCooking();
    }
}

class ResumeCookingCommand implements Command {
    private Microwave microwave;

    public ResumeCookingCommand(Microwave microwave) {
        this.microwave = microwave;
    }

    @Override
    public void execute() {
        microwave.resumeCooking();
    }
}

class FinishCookingCommand implements Command {
    private Microwave microwave;

    public FinishCookingCommand(Microwave microwave) {
        this.microwave = microwave;
    }

    @Override
    public void execute() {
        microwave.finishCooking();
    }
}

class RoastCommand implements Command {
    private Microwave microwave;

    public RoastCommand(Microwave microwave) {
        this.microwave = microwave;
    }

    @Override
    public void execute() {
        microwave.setRoastState();
    }
}

class BakeCommand implements Command {
    private Microwave microwave;

    public BakeCommand(Microwave microwave) {
        this.microwave = microwave;
    }

    @Override
    public void execute() {
        microwave.setBakeState();
    }
}

// State Pattern
interface MicrowaveState {
    void startCooking();
    void pauseCooking();
    void resumeCooking();
    void finishCooking();
}

class GrillingState implements MicrowaveState {
    private Microwave microwave;

    public GrillingState(Microwave microwave) {
        this.microwave = microwave;
    }

    @Override
    public void startCooking() {
        System.out.println("Grilling started.");
        microwave.notifyObservers();
    }

    @Override
    public void pauseCooking() {
        System.out.println("Grilling paused.");
        microwave.notifyObservers();
    }

    @Override
    public void resumeCooking() {
        System.out.println("Grilling resumed.");
        microwave.notifyObservers();
    }

    @Override
    public void finishCooking() {
        System.out.println("Grilling finished.");
        microwave.setState(new IdleState(microwave));
        microwave.notifyObservers();
    }
}

class CookState implements MicrowaveState {
    private Microwave microwave;

    public CookState(Microwave microwave) {
        this.microwave = microwave;
    }

    @Override
    public void startCooking() {
        System.out.println("Cooking started.");
        microwave.notifyObservers();
    }

    @Override
    public void pauseCooking() {
        System.out.println("Cooking paused.");
        microwave.notifyObservers();
    }

    @Override
    public void resumeCooking() {
        System.out.println("Cooking resumed.");
        microwave.notifyObservers();
    }

    @Override
    public void finishCooking() {
        System.out.println("Cooking finished.");
        microwave.setState(new IdleState(microwave));
        microwave.notifyObservers();
    }
}

class RoastState implements MicrowaveState {
    private Microwave microwave;

    public RoastState(Microwave microwave) {
        this.microwave = microwave;
    }

    @Override
    public void startCooking() {
        System.out.println("Roasting started.");
        microwave.notifyObservers();
    }

    @Override
    public void pauseCooking() {
        System.out.println("Roasting paused.");
        microwave.notifyObservers();
    }

    @Override
    public void resumeCooking() {
        System.out.println("Roasting resumed.");
        microwave.notifyObservers();
    }

    @Override
    public void finishCooking() {
        System.out.println("Roasting finished.");
        microwave.setState(new IdleState(microwave));
        microwave.notifyObservers();
    }
}

class BakeState implements MicrowaveState {
    private Microwave microwave;

    public BakeState(Microwave microwave) {
        this.microwave = microwave;
    }

    @Override
    public void startCooking() {
        System.out.println("Baking started.");
        microwave.notifyObservers();
    }

    @Override
    public void pauseCooking() {
        System.out.println("Baking paused.");
        microwave.notifyObservers();
    }

    @Override
    public void resumeCooking() {
        System.out.println("Baking resumed.");
        microwave.notifyObservers();
    }

    @Override
    public void finishCooking() {
        System.out.println("Baking finished.");
        microwave.setState(new IdleState(microwave));
        microwave.notifyObservers();
    }
}

class IdleState implements MicrowaveState {
    private Microwave microwave;

    public IdleState(Microwave microwave) {
        this.microwave = microwave;
    }

    @Override
    public void startCooking() {
        System.out.println("Microwave is now cooking.");
        microwave.setState(new GrillingState(microwave));
        microwave.notifyObservers();
    }

    @Override
    public void pauseCooking() {
        System.out.println("Microwave is idle. Nothing to pause.");
    }

    @Override
    public void resumeCooking() {
        System.out.println("Microwave is idle. Nothing to resume.");
    }

    @Override
    public void finishCooking() {
        System.out.println("Microwave is already idle.");
    }
}

// Observer Pattern
interface MicrowaveObserver {
    void update();
}

class CookingProgressObserver implements MicrowaveObserver {
    private Microwave microwave;

    public CookingProgressObserver(Microwave microwave) {
        this.microwave = microwave;
    }

    @Override
    public void update() {
        System.out.println("Cooking progress updated.");
    }
}

class TemperatureObserver implements MicrowaveObserver {
    private Microwave microwave;

    public TemperatureObserver(Microwave microwave) {
        this.microwave = microwave;
    }

    @Override
    public void update() {
        System.out.println("Temperature updated.");
    }
}

class TimeObserver implements MicrowaveObserver {
    private Microwave microwave;

    public TimeObserver(Microwave microwave) {
        this.microwave = microwave;
    }

    @Override
    public void update() {
        System.out.println("Time updated.");
    }
}

class Microwave {
    private List<MicrowaveObserver> observers = new ArrayList<>();
    private MicrowaveState state;

    public void addObserver(MicrowaveObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(MicrowaveObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (MicrowaveObserver observer : observers) {
            observer.update();
        }
    }

    public void setState(MicrowaveState state) {
        this.state = state;
    }

    public void setGrillState() {
        this.state = new GrillingState(this);
    }

    public void setCookState() {
        this.state = new CookState(this);
    }

    public void setRoastState() {
        this.state = new RoastState(this);
    }

    public void setBakeState() {
        this.state = new BakeState(this);
    }

    public void startCooking() {
        state.startCooking();
    }

    public void pauseCooking() {
        state.pauseCooking();
    }

    public void resumeCooking() {
        state.resumeCooking();
    }

    public void finishCooking() {
        state.finishCooking();
    }

    public static void main(String[] args) {
        Microwave microwave = new Microwave();
        microwave.addObserver(new CookingProgressObserver(microwave));
        microwave.addObserver(new TemperatureObserver(microwave));
        microwave.addObserver(new TimeObserver(microwave));
        microwave.setGrillState();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a command (start/pause/resume/finish/roast/bake/exit):");
            String userInput = scanner.nextLine();

            Command command = null;

            if (userInput.equals("start")) {
                command = new StartCookingCommand(microwave);
            } else if (userInput.equals("pause")) {
                command = new PauseCookingCommand(microwave);
            } else if (userInput.equals("resume")) {
                command = new ResumeCookingCommand(microwave);
            } else if (userInput.equals("finish")) {
                command = new FinishCookingCommand(microwave);
            } else if (userInput.equals("roast")) {
                command = new RoastCommand(microwave);
            } else if (userInput.equals("bake")) {
                command = new BakeCommand(microwave);
            } else if (userInput.equals("exit")) {
                System.out.println("Exiting the microwave application.");
                break;
            } else {
                System.out.println("Invalid command. Please enter start, pause, resume, finish, roast, bake, or exit.");
                continue;
            }

            if (command != null) {
                command.execute();
            }
        }

        scanner.close();
    }
}

