import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// MenuItem class representing a single item in the menu
class MenuItem {
    private String name;
    private String description;
    private boolean vegetarian;
    private double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public double getPrice() {
        return price;
    }
}

// Menu interface to create a common structure for all menus
interface Menu {
    Iterator<MenuItem> createIterator();
}

// BreakfastMenu class implementing the Menu interface
class BreakfastMenu implements Menu {
    private List<MenuItem> menuItems;

    public BreakfastMenu() {
        menuItems = new ArrayList<>();

        addItem("Pancakes", "Delicious pancakes with syrup", true, 2.99);
        addItem("Omelette", "Three eggs, cheese, and vegetables", false, 3.99);
        addItem("Waffles", "Waffles with fruit toppings", true, 4.99);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.add(menuItem);
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return menuItems.iterator();
    }
}

// LunchMenu class implementing the Menu interface
class LunchMenu implements Menu {
    private MenuItem[] menuItems;
    private int numberOfItems = 0;
    private static final int MAX_ITEMS = 6;

    public LunchMenu() {
        menuItems = new MenuItem[MAX_ITEMS];

        addItem("Veggie Burger", "Vegetarian burger with fries", true, 5.99);
        addItem("Soup of the day", "Soup with side salad", false, 3.69);
        addItem("Burrito", "Burrito with beans and rice", true, 4.29);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        if (numberOfItems >= MAX_ITEMS) {
            System.out.println("Sorry, the menu is full! Can't add more items.");
        } else {
            MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
            menuItems[numberOfItems] = menuItem;
            numberOfItems++;
        }
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return new ArrayIterator(menuItems);
    }
}

// ArrayIterator class to iterate over an array of MenuItems
class ArrayIterator implements Iterator<MenuItem> {
    private MenuItem[] items;
    private int position = 0;

    public ArrayIterator(MenuItem[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return position < items.length && items[position] != null;
    }

    @Override
    public MenuItem next() {
        MenuItem menuItem = items[position];
        position++;
        return menuItem;
    }
}

// DinnerMenu class implementing the Menu interface
class DinnerMenu implements Menu {
    private List<MenuItem> menuItems;

    public DinnerMenu() {
        menuItems = new ArrayList<>();

        addItem("Spaghetti", "Spaghetti with marinara sauce", true, 8.99);
        addItem("Steak", "Grilled steak with vegetables", false, 15.99);
        addItem("Salad", "Fresh salad with various toppings", true, 6.99);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.add(menuItem);
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return menuItems.iterator();
    }
}

// Waitress class that uses the iterators to print out the menus
class Waitress {
    private Menu breakfastMenu;
    private Menu lunchMenu;
    private Menu dinnerMenu;

    public Waitress(Menu breakfastMenu, Menu lunchMenu, Menu dinnerMenu) {
        this.breakfastMenu = breakfastMenu;
        this.lunchMenu = lunchMenu;
        this.dinnerMenu = dinnerMenu;
    }

    public void printMenu() {
        Iterator<MenuItem> breakfastIterator = breakfastMenu.createIterator();
        Iterator<MenuItem> lunchIterator = lunchMenu.createIterator();
        Iterator<MenuItem> dinnerIterator = dinnerMenu.createIterator();

        System.out.println("MENU\n----\nBREAKFAST");
        printMenu(breakfastIterator);
        System.out.println("\nLUNCH");
        printMenu(lunchIterator);
        System.out.println("\nDINNER");
        printMenu(dinnerIterator);
    }

    private void printMenu(Iterator<MenuItem> iterator) {
        while (iterator.hasNext()) {
            MenuItem menuItem = iterator.next();
            System.out.print(menuItem.getName() + ", ");
            System.out.print(menuItem.getPrice() + " -- ");
            System.out.println(menuItem.getDescription());
        }
    }
}

// Main class to test the iterator pattern with menus
public class MenuTest {
    public static void main(String[] args) {
        BreakfastMenu breakfastMenu = new BreakfastMenu();
        LunchMenu lunchMenu = new LunchMenu();
        DinnerMenu dinnerMenu = new DinnerMenu();

        Waitress waitress = new Waitress(breakfastMenu, lunchMenu, dinnerMenu);
        waitress.printMenu();
    }
}
