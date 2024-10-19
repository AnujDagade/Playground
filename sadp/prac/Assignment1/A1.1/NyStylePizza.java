public class NyStylePizza extends PizzaStore {

	Pizza createPizza(int item) {
		if (item==1) {
			return new NyStyleCheesePizza();
		} 
		else if (item==2) {
			return new NyStyleVeggiePizza();
		} 
		else 
			return null;
	}
}
