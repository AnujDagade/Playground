import java.util.*;
//Ceilingfan class to represent ceiling fan
class Ceilingfan {
    private String state;

    public void setState(String state) {
        this.state = state;
        System.out.println("Ceiling Fan is now " + state);
    }

    public String getState() {
        return state;
    }
}
//UndoCommand class to restore the state changes
class UndoCommand {
    private String previousState;
    private Ceilingfan ceilingFan;

    public UndoCommand(Ceilingfan ceilingFan, String previousState) {
        this.ceilingFan = ceilingFan;
        this.previousState = previousState;
    }

//Responsible for undoing state changes by reverting the fan's state to previous state
public void execute() {
        String currentState = ceilingFan.getState();
        ceilingFan.setState(previousState);
        previousState = currentState;
    }
}

//CommandInvoker class to execute and manage commands
class CommandInvoker {
    private Stack<UndoCommand> undoStack = new Stack<>();
    private Ceilingfan ceilingFan;

    public CommandInvoker(Ceilingfan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

//pops the most recent UndoCommand from stack and execute it  to undo last action
public void undo() {
        if (!undoStack.isEmpty()) {
            UndoCommand undoCommand = undoStack.pop();
            undoCommand.execute();
        } else {
            System.out.println("Nothing to undo.");
        }
    }

//To change the fan's state.
public void setFanState(String state) {
        String previousState = ceilingFan.getState();
        ceilingFan.setState(state);
        undoStack.push(new UndoCommand(ceilingFan, previousState));
    }
}

//Menu-driven interface responsible for the user interface
public class CeilingFan {
    public static void main(String[] args) {
        Ceilingfan ceilingFan = new Ceilingfan();
        CommandInvoker invoker = new CommandInvoker(ceilingFan);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCeiling Fan Control Menu");
            System.out.println("1. Turn On");
            System.out.println("2. Turn Off");
            System.out.println("3. Change Speed");
            System.out.println("4. Undo");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    invoker.setFanState("On");
                    break;
                case 2:
                    invoker.setFanState("Off");
                    break;
                case 3:
                    System.out.print("Enter new speed (Low/Medium/High): ");
                    String speed = scanner.next();
                    invoker.setFanState(speed);
                    break;
                case 4:
                    invoker.undo();
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

