import java.util.*;
public class GumballMachineTestDrive {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GumballMachine gumballMachine = new GumballMachine(5);

		while(true){
			System.out.println("\n1.Insert quarter");
			System.out.println("2.Remove quarter");
			System.out.println("3.Turn Crank");
			System.out.println("4.Status");
			System.out.println("5.Refill");
			System.out.println("6.Exit");
			System.out.print("Enter an command: ");

			switch(sc.nextInt()){
				case 1:
					gumballMachine.insertQuarter();
					break;
				case 2:
					gumballMachine.ejectQuarter();
					break;
				case 3:
					gumballMachine.turnCrank();
					break;
				case 4:
					System.out.println(gumballMachine);
					break;
				case 5:
					gumballMachine.refill(5);
                    break;
                case 6:
				System.exit(0);
			}
		}			
	}
}
