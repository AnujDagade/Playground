public class ChicagoStylePizza extends PizzaStore {

	Pizza createPizza(int item) {
		if (item==1) {
			return new ChicagoStyleCheesePizza();
		} 
		else if (item==2) {
			return new ChicagoStyleVeggiePizza();
		} 
		else 
			return null;
	}
}
