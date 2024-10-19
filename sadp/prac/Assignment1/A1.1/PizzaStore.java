abstract class PizzaStore{
	
	abstract Pizza createPizza(int item);
	
	public Pizza orderPizza(int type){
		Pizza pizza=createPizza(type);
		System.out.println("---Making "+pizza.getName()+".....---");
		pizza.prepare();
		pizza.bake();
		pizza.cut();
 		pizza.box();
 		return pizza;
	}

}