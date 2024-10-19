// Pizza Interface
interface Pizza {
    void prepare();
    void bake();
    void cut();
    void box();
    String getName();
}

// NYStyleCheesePizza Class
class NYStyleCheesePizza implements Pizza {
    private String name = "NY Style Cheese Pizza";

    public void prepare() {
        System.out.println("Preparing " + name);
    }

    public void bake() {
        System.out.println("Baking " + name);
    }

    public void cut() {
        System.out.println("Cutting " + name);
    }

    public void box() {
        System.out.println("Boxing " + name);
    }

    public String getName() {
        return name;
    }
}

// ChicagoStyleCheesePizza Class
class ChicagoStyleCheesePizza implements Pizza {
    private String name = "Chicago Style Cheese Pizza";

    public void prepare() {
        System.out.println("Preparing " + name);
    }

    public void bake() {
        System.out.println("Baking " + name);
    }

    public void cut() {
        System.out.println("Cutting " + name);
    }

    public void box() {
        System.out.println("Boxing " + name);
    }

    public String getName() {
        return name;
    }
}

// PizzaStore Interface
interface PizzaStore {
    Pizza createPizza(String type);

    default Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}

// NYPizzaStore Class
class NYPizzaStore implements PizzaStore {
    public Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new NYStyleCheesePizza();
        } else {
            return null; // Can be extended to handle other types
        }
    }
}

// ChicagoPizzaStore Class
class ChicagoPizzaStore implements PizzaStore {
    public Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        } else {
            return null; // Can be extended to handle other types
        }
    }
}

// Main Class
public class PizzaTestDrive {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();
	
        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("vishvajit ordered a " + pizza.getName() + "\n");

        pizza = chicagoStore.orderPizza("cheese");
        System.out.println("sahil ordered a  " + pizza.getName() + "\n");
    }
}

