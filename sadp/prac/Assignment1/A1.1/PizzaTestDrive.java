import java.util.*;
public class PizzaTestDrive {

	public static void main(String[] args) {
		PizzaStore nyStore = new NyStylePizza();
		PizzaStore chicagoStore = new ChicagoStylePizza();
		Scanner sc = new Scanner(System.in);
		System.out.println("Menu: \n1.NyStylePizza \n2.ChicagoStylePizza");
		System.out.println("Enter type of the pizza: ");
		int var1 = sc.nextInt();
		//System.out.println("Enter type of the second pizza: ");
		//int var2 = sc.nextInt();
		if(var1==1){
			Pizza pizza = nyStore.orderPizza(var1);
			System.out.println("Your order was a " + pizza.getName() + "\n");
			}
		else if(var1==2){

		Pizza pizza = chicagoStore.orderPizza(var1);
		System.out.println("Your order was a " + pizza.getName() + "\n");
		}
	}
}
